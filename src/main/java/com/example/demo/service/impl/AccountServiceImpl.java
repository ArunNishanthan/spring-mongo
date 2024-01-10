package com.example.demo.service.impl;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.Address;
import com.example.demo.model.RequestQueue;
import com.example.demo.repositories.AccountInfoRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.DbServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.demo.utils.AppUtils.getIDForAccountInfo;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountInfoRepository accountInfoRepository;
    private final DbServices dbServices;

    @Override
    @Retryable(retryFor = OptimisticLockingFailureException.class)
    public AccountInfo transact(RequestQueue requestQueue) throws Exception {
        var accountInfoId = getIDForAccountInfo(requestQueue.getAccountNumber(), requestQueue.getProductNumber(), requestQueue.getCurrencyCode());
        Optional<AccountInfo> optionalAccountInfo = accountInfoRepository.findById(accountInfoId);
        AccountInfo accountInfo = null;
        if (optionalAccountInfo.isEmpty()) {
            var address = Address.builder().country("India").state("TN").city("Chennai").build();
            accountInfo = AccountInfo.builder().balance(new BigDecimal("10000"))
                    .accountNumber(requestQueue.getAccountNumber()).currencyCode(requestQueue.getCurrencyCode()).productNumber(requestQueue.getProductNumber())
                    .id(accountInfoId)
                    .address(address).build();
        } else {
            accountInfo = optionalAccountInfo.get();
        }
        if (requestQueue.getType().equalsIgnoreCase("C")) {
            accountInfo.setBalance(accountInfo.getBalance().add(requestQueue.getAmount()));
        } else {
            accountInfo.setBalance(accountInfo.getBalance().add(requestQueue.getAmount().negate()));
        }
        return dbServices.save(requestQueue, accountInfo);
    }

}
