package com.example.demo.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Document("acountInfo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AccountInfo {
    @Id
    private AccountInfoId accountInfoId;
    private BigDecimal balance;
    private Address address;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;

}
