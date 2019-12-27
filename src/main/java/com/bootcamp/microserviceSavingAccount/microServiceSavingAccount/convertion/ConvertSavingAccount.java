package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.convertion;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.Movement;
import org.springframework.stereotype.Controller;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto.SavingAccountDto;

@Controller
public class ConvertSavingAccount {

	public SavingAccountDto toSavingAccountDto(SavingAccount savingAccount) {
		SavingAccountDto dto = new SavingAccountDto();
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
		savingAccount.setNomBank(dto.getNomBank());
		savingAccount.setNumAccount(dto.getNumAccount());
		savingAccount.setNomAccount(dto.getNomAccount());
		savingAccount.setTypeAccount(dto.getTypeAccount());
		savingAccount.setCurrentBalance(dto.getCurrentBalance());
		savingAccount.setStatus(dto.getStatus());
		savingAccount.setCreatedAt(dto.getCreatedAt());
		savingAccount.setUpdatedAt(dto.getUpdatedAt());
		savingAccount.setCantTransactions(dto.getCantTransactions());
		return savingAccount;
	}

	public Movement toMovement(SavingAccountDto dto) {
		Movement movement = new Movement();
		movement.setNumAccount(dto.getNumAccount());
		movement.setNumAccount(dto.getNumAccount());
		movement.setNumAccount(dto.getNumAccount());
		return movement;
	}

	
}
