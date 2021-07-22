package ru.raiffeisen.csp.api_manager.integration.mq;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class SomeSystemMqConfig {

    @Bean
    @ConfigurationProperties("resources.some-system.ibm.mq")
    public IbmMqProps someSystemIbmMqProps() {
        return new IbmMqProps();
    }

    @Bean("someSystemMQCF")
    public ConnectionFactory someSystemConnectionFactory() throws JMSException {
        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        factory.setCCSID(1208);
        factory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
        factory.setHostName(someSystemIbmMqProps().getHostName());
        factory.setPort(someSystemIbmMqProps().getPort());
        factory.setChannel(someSystemIbmMqProps().getChannel());
        factory.setQueueManager(someSystemIbmMqProps().getQueueManager());
        return factory;
    }

    @Bean
    public JmsComponent someSystemJmsComponent() throws JMSException {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(someSystemConnectionFactory());
        //jmsComponent.setCacheLevelName("CACHE_CONSUMER");
        //jmsComponent.setMaxConcurrentConsumers(1);

        return jmsComponent;
    }

    @Getter
    @Setter
    public static class IbmMqProps {
        private String hostName;
        private Integer port;
        private String channel;
        private String queueManager;
    }
}
