package com.example.demo.service;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.RequestQueue;

public interface DbServices {
    AccountInfo save(RequestQueue requestQueue, AccountInfo accountInfo);
}
