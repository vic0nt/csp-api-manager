package ru.raiffeisen.csp.api_manager.routes.fis_nrlms;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class NrlmsToFisResponseRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("nrlms:{{resources.ibm.mq.channels.nrlms.response-queue}}").routeId("NrlmsToFis")
                .to("csp:{{resources.ibm.mq.channels.csp.response-queue}}")
                .log(LoggingLevel.DEBUG, "Processing ${body}")
                .end();
    }
}
