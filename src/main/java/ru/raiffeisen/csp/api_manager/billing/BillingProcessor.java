package ru.raiffeisen.csp.api_manager.billing;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Slf4j
public class BillingProcessor implements Processor {

    private static final String APPLICATION_NAME = "csp-billing";

    public void process(Exchange exchange) {
        var event = BillingEvent.builder()
                .applicationName(APPLICATION_NAME)
                .id(exchange.getMessage().getMessageId())
                .routeId(exchange.getFromRouteId())
                .createdTimestamp(OffsetDateTime.now())
                .build();

        log.info("{}", event);

        exchange.getIn().setBody(event);
    }
}