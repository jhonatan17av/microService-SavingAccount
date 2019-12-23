package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Account {

    @NotBlank
    private String numAccount;
    @NotBlank
    private String nomAccount;
    @NotBlank
    private String typeAccount;
    @NotBlank
    private String status;

    public Account() {
    }

    public Account(@NotBlank String numAccount, @NotBlank String nomAccount,
                   @NotBlank String typeAccount, @NotBlank String status) {
        this.numAccount = numAccount;
        this.nomAccount = nomAccount;
        this.typeAccount = typeAccount;
        this.status = status;
    }

}
