package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SavingAccountDto {

    private String id;
    private String numAccount;
    private String currentBalance;
    private Date createdAt;
    private String status;
    private List<PersonDto> personList;

    public SavingAccountDto() {
        personList = new ArrayList<>();
    }

    public void addPerson(PersonDto personDto){
        this.personList.add(personDto);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(String numAccount) {
        this.numAccount = numAccount;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PersonDto> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonDto> personList) {
        this.personList = personList;
    }
}
