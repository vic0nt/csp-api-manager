package ru.raiffeisen.csp.api_manager.billing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.raiffeisen.csp.api_manager.common.JsonUtils;

import java.time.OffsetDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class BillingProcessor implements Processor {

    private static final String APPLICATION_NAME = "csp-billing";
    private final Environment environment;

    @Override
    public void process(Exchange exchange) {
        var event = BillingEvent.builder()
                .applicationName(APPLICATION_NAME)
                .environment(String.join(",", environment.getActiveProfiles()))
                .id(exchange.getMessage().getMessageId())
                .routeId(exchange.getFromRouteId())
                .createdTimestamp(OffsetDateTime.now())
                .build();

        log.info("{}", event);

        exchange.getIn().setBody(JsonUtils.jsonify(event));
    }
}