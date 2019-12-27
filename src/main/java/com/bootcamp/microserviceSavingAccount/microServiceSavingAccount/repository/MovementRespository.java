package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface MovementRespository extends ReactiveMongoRepository<Movement, String> {

    Flux<Movement> findBynumAccount(String numAccount);
    Flux<Movement> findBynumAccountAndcreatedAtBetween(String numAccount, Date firstDate, Date lastDate);


}
