package com.example.demo.config;

import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;


@Configuration
public class MongoConfig {
    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        var transactionOptions = TransactionOptions.builder().writeConcern(WriteConcern.MAJORITY).build();
        return new MongoTransactionManager(dbFactory, transactionOptions);
    }
}
