package com.example.demo.repositories;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.AccountInfoId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountInfoRepository extends MongoRepository<AccountInfo, AccountInfoId> {

}

