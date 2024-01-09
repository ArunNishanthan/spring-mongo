package com.example.demo.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class AccountInfoId {
    private String accountNUmber;
    private String productNumber;
    private String currencyCode;

}
