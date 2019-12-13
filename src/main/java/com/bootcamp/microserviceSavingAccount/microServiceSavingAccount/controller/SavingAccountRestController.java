package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.controller;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.SavingAccountServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.invoke.empty.Empty;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/savingAccount")
public class SavingAccountRestController {

    @Autowired
    private SavingAccountServiceImpl savingAccountService;
    @Autowired
    private ConvertSavingAccount convertSavingAccount;

    @GetMapping("/mov/{numAccount}")
    public Mono<ResponseEntity<SavingAccount>> movimiento(@PathVariable String numAccount) {
        return savingAccountService.movimiento(numAccount)
                .map(savingAccount -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(savingAccount))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

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

    @GetMapping("/numAccount/{numAccount}")
    public Mono<ResponseEntity<SavingAccount>> findByDni(@PathVariable String numAccount) {
        return savingAccountService.findByNumAccount(numAccount)
                .map(savingAccount -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(savingAccount))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> saveAccount(@RequestBody Mono<SavingAccountDto> savingAccountMono) {

        Map<String, Object> respuesta = new HashMap<>();

        return savingAccountMono.flatMap(savingAccount -> {
            return savingAccountService.saveSavingAccount(savingAccount)
                    .map(p -> {
                        respuesta.put("SavingAccount :", savingAccount);
                        return ResponseEntity
                                .created(URI.create("/savingAccount"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(respuesta);
                    });
        }).onErrorResume(throwable -> {
            return Mono.just(throwable).cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldError -> "El campo" + fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collectList()
                    .flatMap(list -> {
                        respuesta.put("Errors : ", list);
                        respuesta.put("timestamp : ", new Date());
                        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
                        return Mono.just(ResponseEntity.badRequest().body(respuesta));
                    });
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<SavingAccount>> updateSavingAccount(@RequestBody SavingAccount savingAccount, @PathVariable String id) {
        return savingAccountService.findById(id)
                .flatMap(s ->  {
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

}
