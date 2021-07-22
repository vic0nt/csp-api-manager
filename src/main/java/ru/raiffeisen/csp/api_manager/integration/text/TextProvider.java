package ru.raiffeisen.csp.api_manager.integration.text;

import org.springframework.stereotype.Service;

@Service
public class TextProvider {

    public String sayHelloMq() {
        return "Hello MQ!";
    }

    public String sayHelloKafka() {
        return "Hello Kafka!";
    }

    public String sayHelloWorld() {
        return "Hello World!";
    }

}
