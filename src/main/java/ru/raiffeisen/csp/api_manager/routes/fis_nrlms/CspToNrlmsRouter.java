package ru.raiffeisen.csp.api_manager.routes.fis_nrlms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class CspToNrlmsRouter extends RouteBuilder {

    @Override
    public void configure() {
        //todo uncomment after queues rollout
        //from("ibm:{{resources.ibm.mq.csp.request-queue}}").routeId("CspToNrlms")
        //        .to("ibm:{{resources.ibm.mq.nrlms.request-queue}}")
        //        .end()
        //        .log(LoggingLevel.DEBUG, "Processing ${body}")
        //        .to("stream:out");
    }
}
