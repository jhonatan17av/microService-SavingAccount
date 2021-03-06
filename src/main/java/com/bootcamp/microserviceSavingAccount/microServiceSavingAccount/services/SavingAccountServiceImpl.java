package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.configuration.Constants;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion.ConvertSavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.AccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDtoReturn;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.PersonDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.MovementRespository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.repository.SavingAccountRepository;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.services.serviceDto.IPersonServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class SavingAccountServiceImpl implements ISavingAccountService {

  @Autowired
  private SavingAccountRepository repoSavingAccount;
  @Autowired
  private MovementRespository repoMovement;
  @Autowired
  private IPersonServiceDto personService;
  @Autowired
  private ConvertSavingAccount conv;

  private static final Logger LOG =
      LoggerFactory.getLogger(SavingAccountServiceImpl.class);


  @Override
  public Flux<SavingAccount> findAll() {
    return repoSavingAccount.findAll();
  }

  @Override
  public Mono<SavingAccount> findById(String id) {
    return repoSavingAccount.findById(id);
  }

  @Override
  public Mono<SavingAccount> findByNumAccount(String numAccount) {
    return repoSavingAccount.findBynumAccount(numAccount);
  }

  @Override
  public Flux<SavingAccount> findByNomBank(String nomBank) {
    return repoSavingAccount.findBynomBank(nomBank);
  }

  @Override
  public Mono<SavingAccountDto> saveSavingAccount(SavingAccountDto savingAccountDto) {

    if (savingAccountDto.getNumAccount() == null || savingAccountDto.getNumAccount().equalsIgnoreCase("null")) {
      savingAccountDto.setNumAccount(Constants.NUM_ACCOUNT);
    }

    if (savingAccountDto.getNomAccount() == null || savingAccountDto.getNomAccount().equalsIgnoreCase("null")) {
      savingAccountDto.setNomAccount(Constants.NOM_ACCOUNT);
    }

    return repoSavingAccount.save(conv.toSavingAccount(savingAccountDto))
        .flatMap(savingAccount -> {
          savingAccountDto.getListPersons().forEach(person -> {
            person.setNomBank(savingAccount.getNomBank());
            person.setNumAccount(savingAccount.getNumAccount());
            person.setNomAccount(savingAccount.getNomAccount());
            person.setTypeAccount(savingAccount.getTypeAccount());
            person.setStatus(savingAccount.getStatus());
            personService.savePerson(person).block();
          });
          return Mono.just(savingAccountDto);
        });
  }

  @Override
  public Mono<SavingAccount> updateAccount(SavingAccount savingAccount) {
    return repoSavingAccount.save(savingAccount);
  }

  @Override
  public Mono<Void> delete(SavingAccount savingAccount) {
    return repoSavingAccount.delete(savingAccount);
  }

  @Override
  public Mono<SavingAccount> saveAccountOnPerson(SavingAccount savingAccount, String numDoc) {

    return personService.lstAccounts(numDoc)
        .collectList()
        .flatMap(accounts -> {

          boolean value = false;

          for (AccountDto account : accounts) {
            if (account.getNomAccount().equals(savingAccount.getNomAccount())
                && account.getTypeAccount().equals(savingAccount.getTypeAccount())
                && account.getNomBank().equalsIgnoreCase(savingAccount.getNomBank())) {
              value = true;
              break;
            }
          }

          if (savingAccount.getNomAccount() == null || savingAccount.getNomAccount().equalsIgnoreCase("null")) {
            savingAccount.setNomAccount(Constants.NOM_ACCOUNT);
          }
          if (!value) {
            return repoSavingAccount.save(savingAccount)
                .flatMap(x -> {
                  return personService.findBynumDoc(numDoc)
                      .flatMap(personDtoReturn -> {
                        PersonDto p = new PersonDto();
                        p.setNamePerson(personDtoReturn.getNamePerson());
                        p.setLastName(personDtoReturn.getLastName());
                        p.setTypeDoc(personDtoReturn.getTypeDoc());
                        p.setNumDoc(personDtoReturn.getNumDoc());
                        p.setGender(personDtoReturn.getGender());
                        p.setDateBirth(personDtoReturn.getDateBirth());
                        p.setCreatedAt(personDtoReturn.getCreatedAt());
                        p.setUpdatedAt(personDtoReturn.getUpdatedAt());
                        p.setNomBank(x.getNomBank());
                        p.setNumAccount(x.getNumAccount());
                        p.setNomAccount(x.getNomAccount());
                        p.setTypeAccount(x.getTypeAccount());
                        p.setStatus(x.getStatus());
                        return personService.updatePerson(p, numDoc)
                            .flatMap(personDto1 -> {
                              personDto1.setId(savingAccount.getId());
                              return Mono.just(savingAccount);
                            });
                      });
                });
          } else {
            return Mono.empty();
          }
        });
  }

  @Override
  public Mono<SavingAccount> saveMovement(Movement movement) {
    return repoSavingAccount.findBynumAccount(movement.getNumAccount())

        .flatMap(savingAccount -> {
          double comi = 0.0;

          if (movement.getTypeMovement().equalsIgnoreCase("retiro") && savingAccount.getCurrentBalance() > movement.getBalanceTransaction()) {

            if (savingAccount.getCantTransactions() > 5) {
              movement.setCommission(movement.getBalanceTransaction() * 0.1);
            } else {
              movement.setCommission(comi);
            }

            movement.setCreatedAt(new Date());

            return repoMovement.save(movement)
                .flatMap(m -> {
                  savingAccount.setCantTransactions(savingAccount.getCantTransactions() + 1);
                  savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() - movement.getBalanceTransaction() - movement.getCommission());
                  return repoSavingAccount.save(savingAccount);
                });

          } else if (movement.getTypeMovement().equalsIgnoreCase("deposito")) {

            if (savingAccount.getCantTransactions() > 5) {
              movement.setCommission(movement.getBalanceTransaction() * 0.1);
            } else {
              movement.setCommission(comi);
            }
            movement.setCreatedAt(new Date());

            return repoMovement.save(movement).
                flatMap(m -> {
                  savingAccount.setCantTransactions(savingAccount.getCantTransactions() + 1);
                  savingAccount.setCurrentBalance(savingAccount.getCurrentBalance() + movement.getBalanceTransaction() - movement.getCommission());
                  return repoSavingAccount.save(savingAccount);
                });
          }
          return Mono.just(savingAccount);
        });

  }

  @Override
  public Flux<Movement> findAllMovement() {
    return repoMovement.findAll();
  }

  @Override
  public Flux<Movement> findMovByNumAccount(String numAccount) {
    return repoMovement.findBynumAccount(numAccount);
  }

  @Override
  public Flux<Movement> findByNumAccountAndDateCreated(String numAccount, String firstDate, String lastDate) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return repoMovement.findByNumAccountAndCreatedAtBetween(numAccount, formatter.parse(firstDate), formatter.parse(lastDate));
  }


}
