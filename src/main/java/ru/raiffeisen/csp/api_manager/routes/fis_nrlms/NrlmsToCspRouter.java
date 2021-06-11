package ru.raiffeisen.csp.api_manager.routes.fis_nrlms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class NrlmsToCspRouter extends RouteBuilder {

    @Override
    public void configure() {
        //todo uncomment after queues rollout
        //from("ibm:{{resources.ibm.mq.nrlms.response-queue}}").routeId("NrlmsToCsp")
        //        .to("ibm:{{resources.ibm.mq.csp.response-queue}}")
        //        .end()
        //        .log(LoggingLevel.DEBUG, "Processing ${body}")
        //        .to("stream:out");
    }
}
