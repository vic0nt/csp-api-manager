package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class TextToIbmMqRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:hello-mq?period={{timer.period}}").routeId("hello-mq")
                .autoStartup("{{application.camel.demo-routes-autostartup}}")
                .transform().method("textProvider", "saySomething")
                .to("csp:DEV.QUEUE.1")
                .end()
                .to("stream:out");
    }
}
