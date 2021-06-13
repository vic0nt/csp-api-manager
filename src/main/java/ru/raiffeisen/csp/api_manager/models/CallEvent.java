package ru.raiffeisen.csp.api_manager.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "csp-api-manager-events-index")
public class CallEvent {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "operationType")
    private String operationType;

    @Field(type = FieldType.Text, name = "systemName")
    private String systemName;

    @Field(type = FieldType.Text, name = "localProductCode")
    private String localProductCode;

    @Field(type = FieldType.Text, name = "groupProductCode")
    private String groupProductCode;

    @Field(type = FieldType.Text, name = "cnum")
    private String cnum;

    @Field(type = FieldType.Date, name = "created")
    private String created;

}
