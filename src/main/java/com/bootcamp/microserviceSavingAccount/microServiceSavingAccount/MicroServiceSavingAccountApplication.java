package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.SavingAccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
public class MicroServiceSavingAccountApplication implements CommandLineRunner {

	@Autowired
	private SavingAccountRepository dao;
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	private static final Logger log = LoggerFactory.getLogger(MicroServiceSavingAccountApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(MicroServiceSavingAccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mongoTemplate.dropCollection("savingAccounts").subscribe();

		Flux.just(new SavingAccount("12345678910","Cuenta de Ahorro","Vip" ,10000.00,"Active",new Date(),new Date()))
				.flatMap(savingAccount -> dao.save(savingAccount))
				.subscribe(savingAccount -> log.info("SavingAccount inserted :" + savingAccount.getNumAccount()));

	}
}
