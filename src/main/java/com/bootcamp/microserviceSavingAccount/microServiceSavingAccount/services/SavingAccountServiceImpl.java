package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.SavingAccountRepository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto.IPersonServiceDto;


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
    public Mono<SavingAccountDto> save(SavingAccountDto savingAccountDto) {
        return repository.save(conv.toSavingAccount(savingAccountDto))
        		.flatMap(savingAccount -> Mono.just(conv.toSavingAccountDto(savingAccount)))
        		.flatMap(dto -> {
        			savingAccountDto.getListPersons().forEach(personDto -> {
        				personDto.putListNumAccount(dto.getNomAccount(), dto.getNumAccount());
        				dto.addListPerson(personService.savePerson(personDto).block());
        			});
        			return Mono.just(savingAccountDto);
        		});
    }

    @Override
    public Mono<Void> delete(SavingAccount savingAccount) {
        return repository.delete(savingAccount);
    }
}
