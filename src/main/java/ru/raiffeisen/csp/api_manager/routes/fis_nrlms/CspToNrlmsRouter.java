package ru.raiffeisen.csp.api_manager.routes.fis_nrlms;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class CspToNrlmsRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("csp:{{resources.ibm.mq.channels.csp.request-queue}}").routeId("CspToNrlms")
                .to("nrlms:{{resources.ibm.mq.channels.nrlms.request-queue}}")
                .end()
                .log(LoggingLevel.DEBUG, "Processing ${body}")
                .to("stream:out");
    }
}
