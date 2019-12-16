package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto_toMovement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.MovementRespository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.SavingAccountRepository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto.IPersonServiceDto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SavingAccountServiceImpl implements ISavingAccountService {

    @Autowired
    private SavingAccountRepository repositorySavingAccount;
    @Autowired
    private MovementRespository respositoryMovement;
    @Autowired
    private IPersonServiceDto personService;
    @Autowired
    private ConvertSavingAccount conv;

    private static final Logger LOG =
            LoggerFactory.getLogger(SavingAccountServiceImpl.class);


    @Override
    public Flux<SavingAccount> findAll() {
        return repositorySavingAccount.findAll();
    }

    @Override
    public Mono<SavingAccount> findById(String id) {
        return repositorySavingAccount.findById(id);
    }

    @Override
    public Mono<SavingAccount> findByNumAccount(String numAccount) {
        return repositorySavingAccount.findBynumAccount(numAccount);
    }

    @Override
    public Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto) {

        return repositorySavingAccount.save(conv.toSavingAccount(savingAccountDto))
                .flatMap(savingAccount -> {
                    savingAccountDto.getListPersons().forEach(person -> {
                        person.setIdAccount(savingAccount.getId());
                        person.setNumAccount(savingAccount.getNumAccount());
                        person.setNomAccount(savingAccount.getNomAccount());
                        person.setTypeAccount(savingAccount.getTypeAccount());
                        personService.savePerson(person).block();
                    });
                    return Mono.just(savingAccountDto);
                });
    }

    @Override
    public Mono<SavingAccount> updateAccount(SavingAccount savingAccount) {
        return repositorySavingAccount.save(savingAccount);
    }

    @Override
    public Mono<Void> delete(SavingAccount savingAccount) {
        return repositorySavingAccount.delete(savingAccount);
    }

    @Override
    public Mono<SavingAccount> movimiento(String numAccount) {
        double nuevoMonto = 150.0;

        return repositorySavingAccount.findBynumAccount(numAccount)
                .flatMap(savingAccount -> {
                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() + nuevoMonto);
                    return Mono.just(repositorySavingAccount.save(savingAccount)).block();
                });
    }

    @Override
    public Mono<SavingAccount> movimientoConObjeto(Movement movement) {

        return repositorySavingAccount.findBynumAccount(movement.getNumAccount())
                .flatMap(savingAccount -> {

                    movement.setCreatedAt(new Date());
                    return respositoryMovement.save(movement)
                            .flatMap(s -> {
                                if (movement.getTypeMovement().equals("deposito")) {
                                    savingAccount.setUpdatedAt(new Date());
                                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() + movement.getBalanceTransaction());
                                    return repositorySavingAccount.save(savingAccount);
                                } else {
                                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() - movement.getBalanceTransaction());
                                    return repositorySavingAccount.save(savingAccount);
                                }
                            });

                });
    }

    @Override
    public Mono<Movement> saveMovement(Movement movement) {
        return null;
    }

    @Override
    public Flux<Movement> findAllMovement() {
        return respositoryMovement.findAll();
    }
}
