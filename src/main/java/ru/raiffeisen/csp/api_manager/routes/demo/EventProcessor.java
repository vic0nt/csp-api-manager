package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.raiffeisen.csp.api_manager.models.CallEvent;

import java.util.HashMap;
import java.util.Map;

public class EventProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        //CallEvent event = exchange.getIn().getBody(CallEvent.class);
        String event = exchange.getIn().getBody(String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("value", event);
        //map.put("id", event.getId());
        //map.put("operationType", event.getOperationType());
        exchange.getIn().setBody(map);
    }
}
