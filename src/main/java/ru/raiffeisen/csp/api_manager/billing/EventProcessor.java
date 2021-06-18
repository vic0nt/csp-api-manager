package ru.raiffeisen.csp.api_manager.billing;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.raiffeisen.csp.api_manager.billing.CallEvent;

import java.util.Map;

public class EventProcessor implements Processor {
    public void process(Exchange exchange) {

        var event = (CallEvent) exchange.getIn().getBody();

        Map<String, Object> map = Map.of(
                "id", event.getId(),
                "operationType", event.getOperationType(),
                "systemName", event.getSystemName(),
                "localProductCode", event.getLocalProductCode(),
                "groupProductCode", event.getGroupProductCode(),
                "cnum", event.getCnum(),
                "created", event.getCreated()
        );
        exchange.getIn().setBody(map);
    }
}