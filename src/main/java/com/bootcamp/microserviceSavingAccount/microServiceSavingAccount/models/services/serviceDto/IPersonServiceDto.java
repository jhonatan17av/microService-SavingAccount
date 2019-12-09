package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.services.serviceDto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

    public Flux<PersonDto> findAllPerson();
    public Flux<PersonDto> findByIdPerson(String idPerson);
    public Mono<PersonDto> savePerson(PersonDto personDto);

}
