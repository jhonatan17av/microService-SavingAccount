package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.MovementRespository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.SavingAccountRepository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto.IPersonServiceDto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.dc.path.PathError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class SavingAccountServiceImpl implements ISavingAccountService {

    @Autowired
    private SavingAccountRepository repoSavingAccount;
    @Autowired
    private MovementRespository repoMovement;
    @Autowired
    private IPersonServiceDto personService;
    @Autowired
    private ConvertSavingAccount conv;

    @Autowired
    private Validator validator;

    private static final Logger LOG =
            LoggerFactory.getLogger(SavingAccountServiceImpl.class);


    @Override
    public Flux<SavingAccount> findAll() {
        return repoSavingAccount.findAll();
    }

    @Override
    public Mono<SavingAccount> findById(String id) {
        return repoSavingAccount.findById(id);
    }

    @Override
    public Mono<SavingAccount> findByNumAccount(String numAccount) {
        return repoSavingAccount.findBynumAccount(numAccount);
    }


    @Override
    public Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto) {

        List<String> documents = new ArrayList<>();

        savingAccountDto.getListPersons().forEach(person -> documents.add(person.getNumDoc()));

        documents.forEach(numDoc -> {
            if (personService.findBynumDoc(numDoc) != null) {
                return null;
            } else {
                return repoSavingAccount.save(conv.toSavingAccount(savingAccountDto))
                        .flatMap(savingAccount -> {
                            savingAccountDto.getListPersons().forEach(person -> {
                                person.setNumAccount(savingAccount.getNumAccount());
                                person.setNomAccount(savingAccount.getNomAccount());
                                person.setTypeAccount(savingAccount.getTypeAccount());
                                person.setStatus(savingAccount.getStatus());
                                personService.savePerson(person).block();
                            });
                            return Mono.just(savingAccountDto);
                        });
            }
        });
        return Mono.just(savingAccountDto);
    }


   /*@Override
    public Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto) {

        return repoSavingAccount.save(conv.toSavingAccount(savingAccountDto))
                .flatMap(savingAccount -> {
                    savingAccountDto.getListPersons().forEach(person -> {
                        person.setNumAccount(savingAccount.getNumAccount());
                        person.setNomAccount(savingAccount.getNomAccount());
                        person.setTypeAccount(savingAccount.getTypeAccount());
                        person.setStatus(savingAccount.getStatus());
                        personService.savePerson(person).block();
                    });
                    return Mono.just(savingAccountDto);
                });
    }*/

    @Override
    public Mono<SavingAccount> updateAccount(SavingAccount savingAccount) {
        return repoSavingAccount.save(savingAccount);
    }

    @Override
    public Mono<Void> delete(SavingAccount savingAccount) {
        return repoSavingAccount.delete(savingAccount);
    }

    @Override
    public Mono<SavingAccount> saveMovement(Movement movement) {

        return repoSavingAccount.findBynumAccount(movement.getNumAccount())
                .flatMap(savingAccount -> {

                    movement.setCreatedAt(new Date());
                    return repoMovement.save(movement)
                            .flatMap(s -> {
                                if (movement.getTypeMovement().trim().toLowerCase().equals("deposito")) {
                                    savingAccount.setUpdatedAt(new Date());
                                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() + movement.getBalanceTransaction());
                                    return repoSavingAccount.save(savingAccount);
                                } else if (movement.getTypeMovement().trim().toLowerCase().equals("retiro")) {
                                    savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() - movement.getBalanceTransaction());
                                    return repoSavingAccount.save(savingAccount);
                                }
                                return Mono.just(savingAccount);
                            });
                });
    }

    @Override
    public Flux<Movement> findAllMovement() {
        return repoMovement.findAll();
    }

    @Override
    public Flux<Movement> findMovByNumAccount(String numAccount) {
        return repoMovement.findBynumAccount(numAccount);
    }

}
