package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

import java.math.BigDecimal;

public class ObterBoletoRequest {

    private String codigoDeBarras;
    private BigDecimal valor;

    public ObterBoletoRequest() {
    }

    public ObterBoletoRequest(BoletoForm boletoForm) {
        this.codigoDeBarras = boletoForm.getCodigoDeBarras();
        this.valor = boletoForm.getValor();
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
