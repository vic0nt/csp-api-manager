package ru.raiffeisen.csp.api_manager.billing;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CallEvent {

    private final String id;
    private final String operationType;
    private final String systemName;
    private final String localProductCode;
    private final String groupProductCode;
    private final String cnum;
    private final String created;
}
