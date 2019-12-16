package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto_toMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISavingAccountService {

    Flux<SavingAccount> findAll();
    Mono<SavingAccount> findById(String id);
    Mono<SavingAccount> findByNumAccount(String numAccount);
    Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto);
    Mono<SavingAccount> updateAccount(SavingAccount savingAccount);
    Mono<Void> delete(SavingAccount savingAccount);
    Mono<SavingAccount> movimiento(String numAccount);
    Mono<SavingAccount> movimientoConObjeto(Movement movement);

    Mono<Movement> saveMovement(Movement movement);
    Flux<Movement> findAllMovement();

}
