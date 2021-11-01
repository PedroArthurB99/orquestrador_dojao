package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital.TransacaoForm;

import java.math.BigDecimal;

public class OperacaoRequest {

    private BigDecimal valor;

    @Deprecated
    public OperacaoRequest() {
    }

    public OperacaoRequest(BigDecimal valor) {
        this.valor = valor;
    }

    public OperacaoRequest(TransacaoForm operacaoForm) {
        this.valor = operacaoForm.getValor();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
