package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Document(collection = "savingAccounts")
public class SavingAccount {

    @NotBlank
    @Id
    private String id;
    @NotEmpty(message = "Campo nomBank no puede ser vacio")
    private String nomBank;
    @NotEmpty(message = "Campo numAccount no puede ser vacio")
    private String numAccount;
    @NotEmpty(message = "Campo nomAccount no puede ser vacio")
    private String nomAccount;
    @NotEmpty(message = "Campo typeAccount no puede ser vacio")
    private String typeAccount;
    @NotEmpty(message = "Campo currentBalance no puede ser vacio")
    @Min(value = 0,message = "Valor minimo 0")
    private Double currentBalance;
    @NotEmpty(message = "Campo status no puede ser vacio")
    private String status;
    @NotEmpty(message = "Campo createdAt no puede ser vacio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @NotEmpty(message = "Campo updatedAt no puede ser vacio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;
    @NotEmpty(message = "Campo cantTransactions no puede ser vacio")
    private Integer cantTransactions;

    public SavingAccount() {
    }

    public SavingAccount(@NotEmpty(message = "Campo nomBank no puede ser vacio")
                                 String nomBank,
                         @NotEmpty(message = "Campo numAccount no puede ser vacio")
                                 String numAccount,
                         @NotEmpty(message = "Campo nomAccount no puede ser vacio")
                                 String nomAccount,
                         @NotEmpty(message = "Campo typeAccount no puede ser vacio")
                                 String typeAccount,
                         @NotEmpty(message = "Campo currentBalance no puede ser vacio")
                         @Min(value = 0, message = "Valor minimo 0")
                                 Double currentBalance,
                         @NotEmpty(message = "Campo status no puede ser vacio")
                                 String status,
                         @NotEmpty(message = "Campo createdAt no puede ser vacio")
                                 Date createdAt,
                         @NotEmpty(message = "Campo updatedAt no puede ser vacio")
                                 Date updatedAt,
                         @NotEmpty(message = "Campo cantTransactions no puede ser vacio")
                                 Integer cantTransactions) {
        this.nomBank = nomBank;
        this.numAccount = numAccount;
        this.nomAccount = nomAccount;
        this.typeAccount = typeAccount;
        this.currentBalance = currentBalance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cantTransactions = cantTransactions;
    }
}
