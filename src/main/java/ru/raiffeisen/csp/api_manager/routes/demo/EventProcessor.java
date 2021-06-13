package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

public class EventProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String event = exchange.getIn().getBody(String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("title", event);
        exchange.getIn().setBody(map);
    }
}
