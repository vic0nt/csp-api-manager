package ru.raiffeisen.csp.api_manager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import ru.raiffeisen.csp.api_manager.models.CallEvent;

@Service
@Slf4j
public class CallEventService {

    private static final String EVENT_INDEX = "csp-api-manager-events-index";

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public CallEventService(final ElasticsearchOperations elasticsearchOperations) {
        super();
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public String createCallEventIndex(CallEvent event) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(event.getId()).withObject(event).build();
        return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(EVENT_INDEX));
    }

}
