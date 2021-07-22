package ru.raiffeisen.csp.api_manager.integration.mq;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;
import io.vavr.Tuple;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class IbmMqConfig {

    @Bean
    @ConfigurationProperties("resources.ibm.mq")
    public IbmMqProps ibmMqProps() {
        return new IbmMqProps();
    }

    @Bean("csp")
    public JmsComponent cspIbmJmsComponent() {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(ibmMqFactories().get("csp"));

        return jmsComponent;
    }

    @Bean("nrlms")
    public JmsComponent nrlmsIbmJmsComponent() {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(ibmMqFactories().get("nrlms"));

        return jmsComponent;
    }

    @Bean
    public Map<String, ConnectionFactory> ibmMqFactories() {
        return ibmMqProps().channels.entrySet().stream()
                .map(systemNameAndQueues -> Tuple.of(systemNameAndQueues.getKey(), Try.of(
                        () -> {
                            MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
                            factory.setCCSID(1208);
                            factory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
                            factory.setHostName(ibmMqProps().getHostName());
                            factory.setPort(ibmMqProps().getPort());
                            factory.setChannel(systemNameAndQueues.getValue().getChannel());
                            factory.setQueueManager(ibmMqProps().getQueueManager());
                            return factory;
                        })
                        .onFailure(throwable -> log.info("Unable to initialize IBM connection factory", throwable))
                        .get())
                )
                .collect(Collectors.toMap(
                        channelAndConnectionFactory -> channelAndConnectionFactory._1,
                        channelAndConnectionFactory -> channelAndConnectionFactory._2));
    }

    @Getter
    @Setter
    public static class IbmMqProps {
        private String hostName;
        private Integer port;
        private String queueManager;
        private Map<String, Channel> channels;

        @Getter
        @Setter
        public static class Channel {
            private String channel;
            private String requestQueue;
            private String responseQueue;
        }
    }
}
