package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.recarga;

import br.com.zupacademy.grupolaranja.orquestrador.controller.recarga.RecargaForm;

import java.math.BigDecimal;

public class RecargaRequest {

    private BigDecimal valor;
    private String numeroTelefone;
    private String operadoraEnum;

    public RecargaRequest(RecargaForm recargaForm) {
        this.valor = recargaForm.getValor();
        this.numeroTelefone = recargaForm.getNumeroTelefone();
        this.operadoraEnum = String.valueOf(recargaForm.getOperadoraEnum());
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getOperadoraEnum() {
        return operadoraEnum;
    }
}
