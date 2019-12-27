package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.AccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDtoReturn;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonServiceDtoImpl implements IPersonServiceDto {

	@Autowired
	private WebClient client;


	@Override
	public Mono<Person> savePerson(Person person) {
		return client.post()
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(person)
				.retrieve()
				.bodyToMono(Person.class);
	}

	@Override
	public Mono<PersonDtoReturn> findBynumDoc(String numDoc) {
		Map<String, Object> params = new HashMap<>();
		params.put("numDoc", numDoc);
		return client.get()
				.uri("/document/{numDoc}",params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonDtoReturn.class);
	}

	@Override
	public Mono<PersonDtoReturn> updatePerson(PersonDto personDto, String numDoc) {
		return client.put()
				.uri("/dto/{numDoc}", Collections.singletonMap("numDoc",numDoc))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(personDto)
				.retrieve()
				.bodyToMono(PersonDtoReturn.class);
	}

	@Override
	public Flux<AccountDto> lstAccounts(String numDoc) {
		return client.get()
				.uri("/lstAccount/{numDoc}",Collections.singletonMap("numDoc",numDoc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountDto.class);
	}
}
