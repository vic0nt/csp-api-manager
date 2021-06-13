package ru.raiffeisen.csp.api_manager.integration.elastic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomeSystemElasticConfig {
    @Bean
    @ConfigurationProperties("resources.some-system.elasticsearch")
    public SomeSystemElasticConfig.ElasticProps someSystemElasticProps() {
        return new SomeSystemElasticConfig.ElasticProps();
    }

    @Getter
    @Setter
    public static class ElasticProps {
        private String hostName;
        private Integer port;
        private String clusterName;
        private String indexName;
    }
}
