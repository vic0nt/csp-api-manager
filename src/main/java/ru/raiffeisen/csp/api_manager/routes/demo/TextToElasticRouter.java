package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextToElasticRouter extends RouteBuilder {

    private String esUri = "elasticsearch-rest://docker-cluster?hostAddresses=localhost:9200&operation=INDEX&indexName=csp-api-manager-events-index";

    @Override
    public void configure() {
        from("timer:hello?period={{timer.period}}").routeId("elastic-test")
                .autoStartup(true)
                .transform().method("textProvider", "saySomething")
                .process(new EventProcessor())
                .to(esUri)
                .to("log:foo")
                .end()
                .to("stream:out");
    }
}
