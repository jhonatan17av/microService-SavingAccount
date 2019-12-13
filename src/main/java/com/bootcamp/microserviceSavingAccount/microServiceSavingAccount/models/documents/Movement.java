package com.bootcamp.microserviceSavingAccount.microServiceSavingAccount.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Document(collection = "movements")
public class Movement {

    @NotBlank
    @Id
    private String id;
    @NotBlank
    private String numCuenta;
    @NotBlank
    private String tipoMovimiento;
    @NotBlank
    private Double montoMovimiento;
    @NotBlank
    private Double saldoActual;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
}
