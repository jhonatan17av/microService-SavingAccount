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
        dto.setStatus(savingAccount.getStatus());
        dto.setCreatedAt(savingAccount.getCreatedAt());
        dto.setUpdateAt(savingAccount.getUpdateAt());
        return dto;
    }

    public SavingAccount toSavingAccount(SavingAccountDto dto) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(dto.getId());
        savingAccount.setNumAccount(dto.getNumAccount());
        savingAccount.setCurrentBalance(dto.getCurrentBalance());
        savingAccount.setStatus(dto.getStatus());
        savingAccount.setCreatedAt(dto.getCreatedAt());
        savingAccount.setUpdateAt(dto.getUpdateAt());
        return savingAccount;
    }

}
