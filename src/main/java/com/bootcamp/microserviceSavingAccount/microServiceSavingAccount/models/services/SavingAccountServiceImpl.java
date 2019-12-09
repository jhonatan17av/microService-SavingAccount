package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dao.ISavingAccountDao;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountServiceImpl implements ISavingAccountService {

    @Autowired
    private ISavingAccountDao dao;

    @Override
    public Flux<SavingAccount> findAll() {
        return dao.findAll();
    }

    @Override
    public Mono<SavingAccount> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<SavingAccount> findByNumAccount(String numAccount) {
        return dao.findBynumAccount(numAccount);
    }

    @Override
    public Mono<SavingAccount> save(SavingAccount savingAccount) {
        return dao.save(savingAccount);
    }

    @Override
    public Mono<Void> delete(SavingAccount savingAccount) {
        return dao.delete(savingAccount);
    }
}