package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class PersonDto {

    @NotBlank
    private String id;
    @NotBlank
    private String namePerson;
    @NotBlank
    private String lastName;
    @NotBlank
    private String typeDoc;
    @NotBlank
    private String numDoc;
    @NotBlank
    private String gender;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateBirth;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;
    @NotBlank
    private String nomBank;
    private String numAccount;
    private String nomAccount;
    private String typeAccount;
    private String status;

}
