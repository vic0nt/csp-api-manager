package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class TextToLogRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:hello?period={{timer.period}}").routeId("hello")
                .autoStartup("{{application.camel.demo-routes-autostartup}}")
                .transform().method("textProvider", "sayHelloWorld")
                .filter(simple("${body} contains 'foo'"))
                .to("log:foo")
                .end()
                .to("stream:out");
    }
}
