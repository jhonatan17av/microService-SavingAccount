package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.SavingAccountRepository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto.IPersonServiceDto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountServiceImpl implements ISavingAccountService {

    @Autowired
    private SavingAccountRepository repository;
    @Autowired
    private IPersonServiceDto personService;
    @Autowired
    private ConvertSavingAccount conv;

    private static final Logger LOG =
            LoggerFactory.getLogger(SavingAccountServiceImpl.class);


    @Override
    public Flux<SavingAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<SavingAccount> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<SavingAccount> findByNumAccount(String numAccount) {
        return repository.findBynumAccount(numAccount);
    }

    @Override
    public Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto) {

        return repository.save(conv.toSavingAccount(savingAccountDto))
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
        return repository.save(savingAccount);
    }

    @Override
    public Mono<Void> delete(SavingAccount savingAccount) {
        return repository.delete(savingAccount);
    }

    @Override
    public Mono<SavingAccount> movimiento(String numAccount){
        double nuevoMonto = 150.0;

        return repository.findBynumAccount(numAccount)
                .flatMap(savingAccount -> {
                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() + nuevoMonto);
                    return Mono.just(repository.save(savingAccount)).block();
                });

       /* return repository.findBynumAccount(numAccount)
                .map(savingAccount -> {
                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() + nuevoMonto);
                    return repository.save(savingAccount);
                })
                .block();

        */


    }
}
