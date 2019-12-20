package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;

import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

	Mono<Person> savePerson(Person person);
	Mono<Person> findBynumDoc(String numDoc);
	
}
