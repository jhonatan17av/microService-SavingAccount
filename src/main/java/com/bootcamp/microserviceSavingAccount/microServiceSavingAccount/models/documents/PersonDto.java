package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import java.util.Date;
import java.util.List;

public class PersonDto {

    private String id;
    private String namePerson;
    private String lastName;
    private String typeDoc;
    private String numDoc;
    private String gender;
    private Date dateBirth;
    private List<String> listAccounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public List<String> getListAccounts() {
        return listAccounts;
    }

    public void setListAccounts(List<String> listAccounts) {
        this.listAccounts = listAccounts;
    }
}