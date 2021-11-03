package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

public class ObterBoletoRequest {

    private String codigoDeBarras;

    public ObterBoletoRequest() {
    }

    public ObterBoletoRequest(BoletoForm boletoForm) {
        this.codigoDeBarras = boletoForm.getCodigoDeBarras();
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }
}
