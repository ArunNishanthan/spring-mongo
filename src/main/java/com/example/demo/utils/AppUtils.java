package com.example.demo.utils;

public class AppUtils {
    public static String getIDForAccountInfo(String accountNUmber, String productNumber, String currencyCode) {
        return accountNUmber + "_" + productNumber + "_" + currencyCode;
    }
}
