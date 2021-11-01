package br.com.zupacademy.grupolaranja.orquestrador.controller.recarga;

import java.math.BigDecimal;

public class RecargaForm {

    private Long id;
    private BigDecimal valor;
    private String numeroTelefone;
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
