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
public class IbmMqConfig {

    @Bean
    @ConfigurationProperties("resources.ibm.mq")
    public IbmMqProps ibmMqProps() {
        return new IbmMqProps();
    }

    @Bean
    public ConnectionFactory ibmConnectionFactory() throws JMSException {
        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        factory.setCCSID(1208);
        factory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
        factory.setHostName(ibmMqProps().getHostName());
        factory.setPort(ibmMqProps().getPort());
        factory.setChannel(ibmMqProps().getChannel());
        factory.setQueueManager(ibmMqProps().getQueueManager());
        return factory;
    }

    @Bean("ibm")
    public JmsComponent ibmJmsComponent() throws JMSException {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(ibmConnectionFactory());

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
