package br.com.zupacademy.grupolaranja.orquestrador.controller.boleto;

import java.math.BigDecimal;

public class BoletoForm {

    private Long id;
    private String codigoDeBarras;
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
