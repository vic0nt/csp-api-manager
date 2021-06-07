package ru.raiffeisen.csp.api_manager.integration.text;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextProvider {

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }

}
