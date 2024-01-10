package com.example.demo.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@CompoundIndex(name = "request_queue_idx", def = "{'requestId': 1, 'channelId': 1}", unique = true)
public class RequestQueue {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;
    private String type;
    private String accountNumber;
    private String productNumber;
    private String currencyCode;
    private String requestId;
    private String channelId;
}
