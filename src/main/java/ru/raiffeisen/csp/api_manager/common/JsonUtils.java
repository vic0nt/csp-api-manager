package ru.raiffeisen.csp.api_manager.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setDateFormat(new StdDateFormat().withLocale(Locale.forLanguageTag("ru"))) //DateTimeFormatter.ISO_OFFSET_DATE_TIME
            .registerModule(new JavaTimeModule());

    public static String jsonify(Object value) {
        return Try.of(() -> objectMapper.writeValueAsString(value))
                .onFailure(throwable -> log.error("Something went wrong due object serialization", throwable))
                .getOrElse("");
    }
}
