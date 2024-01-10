package com.example.demo.repositories;

import com.example.demo.model.AccountInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountInfoRepository extends MongoRepository<AccountInfo, String> {

}

