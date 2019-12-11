package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion;

import org.springframework.stereotype.Controller;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccountDto;

@Controller
public class ConvertSavingAccount {

	public SavingAccountDto toSavingAccountDto(SavingAccount savingAccount) {
		SavingAccountDto dto = new SavingAccountDto();
		dto.setId(savingAccount.getId());
		dto.setNumAccount(savingAccount.getNumAccount());
		dto.setNomAccount(savingAccount.getNomAccount());
		dto.setTypeAccount(savingAccount.getTypeAccount());
		dto.setCurrentBalance(savingAccount.getCurrentBalance());
		dto.setStatus(savingAccount.getStatus());
		dto.setCreatedAt(savingAccount.getCreatedAt());
		dto.setUpdatedAt(savingAccount.getUpdatedAt());
		return dto;
	}
	
	public SavingAccount toSavingAccount(SavingAccountDto dto) {
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setId(dto.getId());
		savingAccount.setNumAccount(dto.getNumAccount());
		savingAccount.setNomAccount(dto.getNomAccount());
		savingAccount.setTypeAccount(dto.getTypeAccount());
		savingAccount.setCurrentBalance(dto.getCurrentBalance());
		savingAccount.setStatus(dto.getStatus());
		savingAccount.setCreatedAt(dto.getCreatedAt());
		savingAccount.setUpdatedAt(dto.getUpdatedAt());
		return savingAccount;
	}
	
}
