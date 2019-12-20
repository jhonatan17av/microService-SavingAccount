package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;

import reactor.core.publisher.Mono;

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
				.syncBody(person)
				.retrieve()
				.bodyToMono(Person.class);
	}

	@Override
	public Mono<Person> findBynumDoc(String numDoc) {
		Map<String, Object> params = new HashMap<>();
		params.put("numDoc", numDoc);
		return client.get()
				.uri("/document/{numDoc}",params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Person.class);
	}
}
