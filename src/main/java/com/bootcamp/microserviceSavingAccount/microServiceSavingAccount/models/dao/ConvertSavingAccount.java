package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dao;

import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccount;
import com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents.SavingAccountDto;
import org.springframework.stereotype.Controller;

@Controller
public class ConvertSavingAccount {

    public SavingAccountDto toSavingAccountDto(SavingAccount savingAccount) {
        SavingAccountDto dto = new SavingAccountDto();
        dto.setId(savingAccount.getId());
        dto.setNumAccount(savingAccount.getNumAccount());
        dto.setCurrentBalance(savingAccount.getCurrentBalance());
        dto.setCreatedAt(savingAccount.getCreatedAt());
        dto.setStatus(savingAccount.getStatus());
        return dto;
    }

    public SavingAccount toSavingAccount(SavingAccountDto dto) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(dto.getId());
        savingAccount.setNumAccount(dto.getNumAccount());
        savingAccount.setCurrentBalance(dto.getCurrentBalance());
        savingAccount.setCreatedAt(dto.getCreatedAt());
        savingAccount.setStatus(dto.getStatus());
        return savingAccount;
    }

}
