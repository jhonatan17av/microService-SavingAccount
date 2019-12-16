package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovementRespository extends ReactiveMongoRepository<Movement, String> {
}
