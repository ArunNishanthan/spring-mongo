package com.example.demo.service.impl;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.RequestQueue;
import com.example.demo.repositories.AccountInfoRepository;
import com.example.demo.repositories.RequestQueueRepository;
import com.example.demo.service.DbServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class DbServicesImpl implements DbServices {


    private final AccountInfoRepository accountInfoRepository;

    private final RequestQueueRepository requestQueueRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountInfo save(RequestQueue requestQueue, AccountInfo accountInfo) {
        requestQueueRepository.save(requestQueue);
        return accountInfoRepository.save(accountInfo);
    }
}
