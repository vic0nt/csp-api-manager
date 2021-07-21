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
public class FisIbmMqConfig {

    @Bean
    @ConfigurationProperties("resources.ibm.mq.fis")
    public FisIbmMqProps fisIbmMqProps() {
        return new FisIbmMqProps();
    }

    @Bean("fis")
    public JmsComponent fisIbmJmsComponent() throws JMSException {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(fisIbmMqFactory());

        return jmsComponent;
    }

    @Bean
    public ConnectionFactory fisIbmMqFactory() throws JMSException {
        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        factory.setCCSID(1208);
        factory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
        factory.setHostName(fisIbmMqProps().getHostName());
        factory.setPort(fisIbmMqProps().getPort());
        factory.setChannel(fisIbmMqProps().getChannel());
        factory.setQueueManager(fisIbmMqProps().getQueueManager());

        return factory;
    }

    @Getter
    @Setter
    public static class FisIbmMqProps {
        private String hostName;
        private Integer port;
        private String queueManager;
        private String channel;
    }
}
