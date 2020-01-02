package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountRepository extends ReactiveMongoRepository<SavingAccount, String> {

  Mono<SavingAccount> findBynumAccount(String numAccount);
  Flux<SavingAccount> findBynomBank(String nomBank);

}
