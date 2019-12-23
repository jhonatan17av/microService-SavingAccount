package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

	Mono<Person> savePerson(Person person);
	Mono<PersonDto> updatePerson(PersonDto personDto, String id);
	Mono<PersonDto> findBynumDoc(String numDoc);
	
}
