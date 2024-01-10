package com.example.demo.service;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.RequestQueue;

public interface AccountService {

    AccountInfo transact(RequestQueue requestQueue) throws Exception;
}
