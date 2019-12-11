package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class SavingAccountDto {

    private String id;
    private String numAccount;
    private String nomAccount;
    private String typeAccount;
    private Double currentBalance;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private List<Person> listPersons;
    
    public SavingAccountDto() {
    	listPersons = new ArrayList<>();
    }
    
    public void addListPerson(Person person) {
    	this.listPersons.add(person);
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
	public String getNomAccount() {
		return nomAccount;
	}
	public void setNomAccount(String nomAccount) {
		this.nomAccount = nomAccount;
	}
	public String getTypeAccount() {
		return typeAccount;
	}
	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Person> getListPersons() {
		return listPersons;
	}
	public void setListPersons(List<Person> listPersons) {
		this.listPersons = listPersons;
	}
    
    
	
}
