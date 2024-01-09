package com.example.demo.service;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.RequestQueue;

public interface AccountService {

    AccountInfo save(RequestQueue requestQueue) throws Exception;
}
