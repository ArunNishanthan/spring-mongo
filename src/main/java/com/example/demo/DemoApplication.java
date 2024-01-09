package com.example.demo;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.AccountInfoId;
import com.example.demo.model.Address;
import com.example.demo.repositories.AccountInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@SpringBootApplication
@AllArgsConstructor
@EnableMongoRepositories
@Slf4j
public class DemoApplication  implements CommandLineRunner {

	private final AccountInfoRepository accountInfoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		var accountIngoId = AccountInfoId.builder().accountNUmber("11").currencyCode("INR").productNumber("123").build();
		var address = Address.builder().country("India").state("TN").city("Chennai").build();
		var accountInfo = AccountInfo.builder().balance(new BigDecimal("123.200")).accountInfoId(accountIngoId).createdDate(ZonedDateTime.now()).updatedDate(ZonedDateTime.now()).address(address).build();
		accountInfoRepository.save(accountInfo);
		accountInfoRepository.findAll().forEach(accountInfo1 -> log.info(accountInfo1.toString()) );
	}
}
