package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dao.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dao.ISavingAccountDao;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.services.serviceDto.IPersonServiceDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.services.serviceDto.PersonServiceDto;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountServiceImpl implements ISavingAccountService {

    @Autowired
    private ISavingAccountDao dao;
    @Autowired
    private ConvertSavingAccount convertSavingAccount;
    @Autowired
    private IPersonServiceDto personServiceDto;

    @Override
    public Flux<SavingAccountDto> findAll() {
        return dao.findAll()
                .flatMap(savingAccount ->{
                   SavingAccountDto dto =  convertSavingAccount.toSavingAccountDto(savingAccount);
                   dto.setPersonList(personServiceDto.findByIdPerson(savingAccount.getId()).collectList().block());
                   return Mono.just(dto);
                });
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
