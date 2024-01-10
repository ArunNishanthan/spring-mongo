package com.example.demo.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@CompoundIndex(name = "account_info_idx", def = "{'accountNUmber': 1, 'productNumber': 1,  'currencyCode': 1}", unique = true)
public class AccountInfo {
    @Id
    private String id;
    private String accountNumber;
    private String productNumber;
    private String currencyCode;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal balance;
    private Address address;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    @Version
    private Long version;
}
