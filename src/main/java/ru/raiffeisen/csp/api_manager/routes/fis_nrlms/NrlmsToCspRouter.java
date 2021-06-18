package ru.raiffeisen.csp.api_manager.routes.fis_nrlms;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class NrlmsToCspRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("nrlms:{{resources.ibm.mq.channels.nrlms.response-queue}}").routeId("NrlmsToCsp")
                .to("csp:{{resources.ibm.mq.channels.csp.response-queue}}")
                .end()
                .log(LoggingLevel.DEBUG, "Processing ${body}")
                .to("stream:out");
    }
}
