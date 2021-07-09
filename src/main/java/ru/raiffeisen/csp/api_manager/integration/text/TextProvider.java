package ru.raiffeisen.csp.api_manager.integration.text;

import org.springframework.stereotype.Service;

@Service
public class TextProvider {

    public String saySomething() {
        return "Hello MQ!";
    }

    public String sayHelloKafka() {
        return "Hello Kafka!";
    }

}
