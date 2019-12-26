package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Account;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

	Mono<Person> savePerson(Person person);
	Mono<PersonDto> findBynumDoc(String numDoc);
	Mono<PersonDto> updatePerson(PersonDto2 personDto2, String numDoc);
	Flux<Account> lstAccounts(String numDoc);

	
}
