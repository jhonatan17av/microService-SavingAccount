package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.AccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDtoReturn;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

	Mono<Person> savePerson(Person person);
	Mono<PersonDtoReturn> findBynumDoc(String numDoc);
	Mono<PersonDtoReturn> updatePerson(PersonDto personDto, String numDoc);
	Flux<AccountDto> lstAccounts(String numDoc);

	
}
