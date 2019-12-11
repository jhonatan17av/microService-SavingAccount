package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "savingAccounts")
public class SavingAccount {

    @NotBlank
    @Id
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
    private Date createdAt;
    @NotBlank
    private Date updatedAt;

    public SavingAccount() {
    }

	public SavingAccount(@NotBlank String numAccount, @NotBlank String nomAccount, @NotBlank String typeAccount,
			@NotBlank Double currentBalance, @NotBlank String status, @NotBlank Date createdAt,
			@NotBlank Date updatedAt) {
		this.numAccount = numAccount;
		this.nomAccount = nomAccount;
		this.typeAccount = typeAccount;
		this.currentBalance = currentBalance;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	
    
    
}
