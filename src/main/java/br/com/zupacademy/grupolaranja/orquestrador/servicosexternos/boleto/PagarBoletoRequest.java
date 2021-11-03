package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

import java.math.BigDecimal;

public class PagarBoletoRequest {

    private Long id;
    private String codigoDeBarras;
    private BigDecimal valor;

    public PagarBoletoRequest() {
    }

    public PagarBoletoRequest(BoletoResponse boletoForm) {
        this.id = boletoForm.getId();
        this.codigoDeBarras = boletoForm.getCodigoDeBarras();
        this.valor = boletoForm.getValor();
    }

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
