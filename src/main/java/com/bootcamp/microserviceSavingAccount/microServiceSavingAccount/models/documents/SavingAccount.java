package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.Min;
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
    private String nomAccount;
    @NotBlank
    private String typeAccount;
    @NotBlank
    @Min(value = 0,message = "Valor minimo 0")
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
}
