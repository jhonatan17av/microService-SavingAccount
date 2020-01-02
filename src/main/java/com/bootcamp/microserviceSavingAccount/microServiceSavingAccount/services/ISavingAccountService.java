package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDtoReturn;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Date;

public interface ISavingAccountService {

    Flux<SavingAccount> findAll();
    Mono<SavingAccount> findById(String id);
    Mono<SavingAccount> findByNumAccount(String numAccount);
    Flux<SavingAccount> findByNomBank(String nomBank);
    Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto);
    Mono<SavingAccount> updateAccount(SavingAccount savingAccount);
    Mono<Void> delete(SavingAccount savingAccount);

    Mono<SavingAccount> saveAccountOnPerson(SavingAccount savingAccount, String numDoc);

    Mono<SavingAccount> saveMovement(Movement movement);
    Flux<Movement> findAllMovement();
    Flux<Movement> findMovByNumAccount(String numAccount);
    Flux<Movement> findByNumAccountAndDateCreated(String numAccount, String d1, String d2) throws ParseException;

}
