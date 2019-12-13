package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

@Data
public class SavingAccountDto {

    @NotBlank
    private String id;
    @NotBlank
    private String numAccount;
    @NotBlank
    private String nomAccount;
    @NotBlank
    private String typeAccount;
    @NotBlank
    private Double currentBalance;
    @NotBlank
    private String status;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;
    @NotBlank
    private List<Person> listPersons;
    
    public SavingAccountDto() {
    	listPersons = new ArrayList<>();
    }
    
    public void addListPerson(Person person) {
    	this.listPersons.add(person);
    }

}
