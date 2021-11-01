package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

import java.math.BigDecimal;

public class BoletoRequest {

    private String codigoDeBarras;
    private BigDecimal valor;

    public BoletoRequest(BoletoForm boletoForm) {
        this.codigoDeBarras = boletoForm.getCodigoDeBarras();
        this.valor = boletoForm.getValor();
    }
}
