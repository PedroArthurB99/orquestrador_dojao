package br.com.zupacademy.grupolaranja.orquestrador.controller.recarga;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RecargaForm {

    @Positive
    private Long id;

    @Positive
    private BigDecimal valor;

    @NotBlank
    private String numeroTelefone;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private OperadoraEnum operadoraEnum;

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public OperadoraEnum getOperadoraEnum() {
        return operadoraEnum;
    }
}
