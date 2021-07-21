package ru.raiffeisen.csp.api_manager.integration.mq;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CspActiveMqConfig {

    @Bean
    @ConfigurationProperties("resources.activemq")
    public ActiveMqProps activeMqProps() {
        return new ActiveMqProps();
    }

    @Bean("amq")
    public JmsComponent activeMqJmsComponent() {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(activemqConnectionFactory());

        return jmsComponent;
    }

    @Bean
    public ActiveMQConnectionFactory activemqConnectionFactory() {
        var connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(activeMqProps().getBrokerUrl());

        return connectionFactory;
    }

    @Getter
    @Setter
    public static class ActiveMqProps {
        private String brokerUrl;
    }
}
