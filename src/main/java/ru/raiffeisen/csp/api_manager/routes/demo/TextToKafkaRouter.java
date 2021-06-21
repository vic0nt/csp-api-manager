package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class TextToKafkaRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:hello-kafka?period={{timer.period}}").routeId("hello-kafka")
                .autoStartup("{{application.camel.demo-routes-autostartup}}")
                .transform().method("textProvider", "sayHelloKafka")
                .to("kafka:super-topic?brokers=172.20.0.3:9092")
                .end()
                .to("stream:out");
    }
}
