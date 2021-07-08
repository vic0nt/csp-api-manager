package ru.raiffeisen.csp.api_manager.routes.fis_nrlms;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class FisToNrlmsRequestRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("csp:{{resources.ibm.mq.channels.csp.request-queue}}").routeId("FisToNrlms")
                .to("nrlms:{{resources.ibm.mq.channels.nrlms.request-queue}}")
                .log(LoggingLevel.DEBUG, "Processing ${body}")
                .end()
                .process("billingProcessor")
                .to("kafka:{{resources.kafka.topicName}}?brokers={{resources.kafka.brokers}}");
    }
}