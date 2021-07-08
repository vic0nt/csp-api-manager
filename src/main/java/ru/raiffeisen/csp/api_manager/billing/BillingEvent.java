package ru.raiffeisen.csp.api_manager.billing;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Builder
@ToString
public class BillingEvent {

    private final String applicationName;

    private final String id;
    private final String routeId;
    private final OffsetDateTime createdTimestamp;
}
