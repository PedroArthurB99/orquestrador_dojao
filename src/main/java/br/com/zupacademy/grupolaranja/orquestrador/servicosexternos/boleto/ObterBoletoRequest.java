package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

public class ObterBoletoRequest {

    private String codigoDeBarras;
    private Long clientId;
    private String emailDestinatario;

    public ObterBoletoRequest() {
    }

    public ObterBoletoRequest(BoletoForm boletoForm) {
        this.codigoDeBarras = boletoForm.getCodigoDeBarras();
        this.clientId = boletoForm.getIdConta();
        this.emailDestinatario = boletoForm.getEmailCliente();
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }
}
