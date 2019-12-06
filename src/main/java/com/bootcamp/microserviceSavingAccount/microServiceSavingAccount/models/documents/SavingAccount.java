package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

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
    private Date createdAt;
    @NotBlank
    private String status;

    public SavingAccount() {
    }

    public SavingAccount(@NotBlank String numAccount, @NotBlank String currentBalance, @NotBlank Date createdAt, @NotBlank String status) {
        this.numAccount = numAccount;
        this.currentBalance = currentBalance;
        this.createdAt = createdAt;
        this.status = status;
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
}
