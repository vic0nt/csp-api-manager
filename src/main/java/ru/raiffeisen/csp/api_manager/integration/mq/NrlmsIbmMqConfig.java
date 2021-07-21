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
public class NrlmsIbmMqConfig {

    @Bean
    @ConfigurationProperties("resources.ibm.mq.nrlms")
    public NrlmsIbmMqProps nrlmsIbmMqProps() {
        return new NrlmsIbmMqProps();
    }

    @Bean("nrlms")
    public JmsComponent nrlmsIbmJmsComponent() throws JMSException {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(nrlmsIbmMqFactory());

        return jmsComponent;
    }

    @Bean
    public ConnectionFactory nrlmsIbmMqFactory() throws JMSException {
        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        factory.setCCSID(1208);
        factory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
        factory.setHostName(nrlmsIbmMqProps().getHostName());
        factory.setPort(nrlmsIbmMqProps().getPort());
        factory.setChannel(nrlmsIbmMqProps().getChannel());
        factory.setQueueManager(nrlmsIbmMqProps().getQueueManager());

        return factory;
    }

    @Getter
    @Setter
    public static class NrlmsIbmMqProps {
        private String hostName;
        private Integer port;
        private String queueManager;
        private String channel;
    }
}
