package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISavingAccountService {

    Flux<SavingAccount> findAll();
    Mono<SavingAccount> findById(String id);
    Mono<SavingAccount> findByNumAccount(String numAccount);
    Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto);
    Mono<SavingAccount> updateAccount(SavingAccount savingAccount);
    Mono<Void> delete(SavingAccount savingAccount);

    Mono<PersonDto> validated(SavingAccount savingAccount, String numDoc);

    Mono<SavingAccount> saveMovement(Movement movement);
    Flux<Movement> findAllMovement();
    Flux<Movement> findMovByNumAccount(String numAccount);

}
