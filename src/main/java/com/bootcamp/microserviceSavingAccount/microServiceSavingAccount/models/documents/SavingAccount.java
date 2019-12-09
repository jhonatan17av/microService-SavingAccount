package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Document(collection = "savingAccounts")
public class SavingAccount {

    @NotBlank
    @Id
    private String id;
    @NotBlank
    private String numAccount;
    @NotBlank
    private String currentBalance;
    @NotBlank
    private String status;
    @NotBlank
    private Date createdAt;
    @NotBlank
    private Date updateAt;

    public SavingAccount() {
    }

	public SavingAccount(@NotBlank String numAccount, @NotBlank String currentBalance, @NotBlank String status,
			@NotBlank Date createdAt, @NotBlank Date updateAt) {
		super();
		this.numAccount = numAccount;
		this.currentBalance = currentBalance;
		this.status = status;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
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

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

    
    
}
