package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SavingAccountRepository extends ReactiveMongoRepository<SavingAccount, String> {

    public Mono<SavingAccount> findBynumAccount(String numAccount);

}
