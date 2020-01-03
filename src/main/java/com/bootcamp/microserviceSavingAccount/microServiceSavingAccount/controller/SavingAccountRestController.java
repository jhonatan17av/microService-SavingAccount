package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.controller;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDtoReturn;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.ISavingAccountService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "Controller-Person", description = "Methods on Controller to Person")
@RequestMapping("/savingAccount")
public class SavingAccountRestController {

  @Autowired
  private ISavingAccountService savingAccountService;
  @Autowired
  private ConvertSavingAccount convertSavingAccount;

  @GetMapping
  public Mono<ResponseEntity<Flux<SavingAccount>>> findAllAccount() {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(savingAccountService.findAll()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<SavingAccount>> findByID(@PathVariable String id) {
    return savingAccountService.findById(id)
        .map(savingAccount -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(savingAccount))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/movements/{numAccount}/{firstDate}/{lastDate}")
  public Mono<ResponseEntity<Flux<Movement>>> findNumAccountAndDatecreated(@PathVariable String numAccount, @PathVariable String firstDate, @PathVariable String lastDate) throws ParseException {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(savingAccountService.findByNumAccountAndDateCreated(numAccount, firstDate, firstDate)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/numAccount/{numAccount}")
  public Mono<ResponseEntity<SavingAccount>> findByNumAccout(@PathVariable String numAccount) {
    return savingAccountService.findByNumAccount(numAccount)
        .map(savingAccount -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(savingAccount))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/nomBank/{nomBank}")
  public Mono<ResponseEntity<Flux<SavingAccount>>> findByNomBank(@PathVariable String nomBank) {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(savingAccountService.findByNomBank(nomBank)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping("/onPerson/{numDoc}")
  public Mono<ResponseEntity<SavingAccount>> saveOnPerson(@RequestBody SavingAccount savingAccountMono, @PathVariable String numDoc) {
    return Mono.just(savingAccountMono)
        .flatMap(savingAccountMono1 -> {
          return savingAccountService.saveAccountOnPerson(savingAccountMono, numDoc)
              .map(s -> ResponseEntity.created(URI.create("/savingAccount"))
                  .contentType(MediaType.APPLICATION_JSON).body(s));
        });
  }

  @PostMapping
  public Mono<ResponseEntity<SavingAccountDto>> saveAccount(@RequestBody SavingAccountDto savingAccountMono) {

    return Mono.just(savingAccountMono)
        .flatMap(savingAccountDtoMono -> {
          return savingAccountService.saveSavingAccount(savingAccountMono)
              .map(s -> ResponseEntity.created(URI.create("/savingAccount"))
                  .contentType(MediaType.APPLICATION_JSON).body(s));
        });

  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<SavingAccount>> updateSavingAccount(@RequestBody SavingAccount savingAccount, @PathVariable String id) {
    return savingAccountService.findById(id)
        .flatMap(s -> {
          s.setNumAccount(savingAccount.getNumAccount());
          s.setNomAccount(savingAccount.getNomAccount());
          s.setTypeAccount(savingAccount.getTypeAccount());
          s.setCurrentBalance(savingAccount.getCurrentBalance());
          s.setStatus(savingAccount.getStatus());
          s.setCreatedAt(savingAccount.getCreatedAt());
          s.setUpdatedAt(savingAccount.getUpdatedAt());
          return savingAccountService.updateAccount(s);
        }).map(account -> ResponseEntity
            .created(URI.create("/savingAccount".concat(account.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(account))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteSavingAccount(@PathVariable String id) {
    return savingAccountService.findById(id)
        .flatMap(savingAccount -> {
          return savingAccountService.delete(savingAccount)
              .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/saveMov")
  public Mono<ResponseEntity<SavingAccount>> saveMovement(@RequestBody Movement movement) {
    return savingAccountService.saveMovement(movement)
        .map(savingAccount -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(savingAccount))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/movements")
  public Mono<ResponseEntity<Flux<Movement>>> findAllMovement() {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(savingAccountService.findAllMovement()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/movements/{numAccount}")
  public Mono<ResponseEntity<Flux<Movement>>> findMovByNumAccount(@PathVariable String numAccount) {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(savingAccountService.findMovByNumAccount(numAccount)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


}
