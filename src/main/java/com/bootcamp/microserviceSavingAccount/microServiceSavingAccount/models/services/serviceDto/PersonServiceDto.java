package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class PersonServiceDto implements IPersonServiceDto {

    @Autowired
    private WebClient webClient;

    @Override
    public Flux<PersonDto> findAllPerson() {
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(PersonDto.class));
    }

    @Override
    public Flux<PersonDto> findByIdPerson(String idPerson) {
        return webClient.get()
                .uri("/{id}", Collections.singletonMap("id", idPerson))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PersonDto.class);
    }

    @Override
    public Mono<PersonDto> savePerson(PersonDto personDto) {
        return webClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .syncBody(personDto)
                .retrieve()
                .bodyToMono(PersonDto.class);
    }
}
