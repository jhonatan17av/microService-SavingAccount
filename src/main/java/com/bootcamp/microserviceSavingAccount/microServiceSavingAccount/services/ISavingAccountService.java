package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISavingAccountService {

    public Flux<SavingAccount> findAll();
    public Mono<SavingAccount> findById(String id);
    public Mono<SavingAccount> findByNumAccount(String numAccount);
    public Mono<SavingAccountDto> save(SavingAccountDto savingAccountDto);
    public Mono<Void> delete(SavingAccount savingAccount);

}
