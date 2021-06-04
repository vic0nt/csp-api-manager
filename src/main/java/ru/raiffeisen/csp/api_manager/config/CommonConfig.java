package ru.raiffeisen.csp.api_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CommonConfig {

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }

}
