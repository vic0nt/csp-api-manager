package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class IbmMqToIbmMqRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("csp:DEV.QUEUE.1").routeId("ibm_mq-to-ibm_mq")
                .autoStartup("{{application.camel.demo-routes-autostartup}}")
                .to("nrlms:DEV.QUEUE.2")
                .end()
                .log(LoggingLevel.DEBUG, "Processing ${body}")
                .to("stream:out");
    }
}
