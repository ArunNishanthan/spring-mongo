package com.example.demo.repositories;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.AccountInfoId;
import com.example.demo.model.RequestQueue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestQueueRepository extends MongoRepository<RequestQueue, Integer> {

}

