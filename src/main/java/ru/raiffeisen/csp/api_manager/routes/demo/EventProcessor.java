package ru.raiffeisen.csp.api_manager.routes.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.raiffeisen.csp.api_manager.models.CallEvent;

import java.util.HashMap;
import java.util.Map;

public class EventProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {

        var event = (CallEvent) exchange.getIn().getBody();
        Map<String, Object> map = new HashMap<>();
        map.put("id", event.getId());
        map.put("operationType", event.getOperationType());
        map.put("systemName", event.getSystemName());
        map.put("localProductCode", event.getLocalProductCode());
        map.put("groupProductCode", event.getGroupProductCode());
        map.put("cnum", event.getCnum());
        map.put("created", event.getCreated());
        exchange.getIn().setBody(map);
    }
}
