package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dao;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ISavingAccountDao extends ReactiveMongoRepository<SavingAccount, String> {

    public Mono<SavingAccount> findBynumAccount(String numAccount);

}
