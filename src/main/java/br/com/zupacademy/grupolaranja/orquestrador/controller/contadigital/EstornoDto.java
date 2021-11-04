package br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital;

import java.math.BigDecimal;

public class EstornoDto {
    private BigDecimal valor;
    private TipoTransacaoEnum tipoTransacao;

    public EstornoDto(BigDecimal valor, TipoTransacaoEnum tipoTransacao) {
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
    }


    @Override
    public String toString() {
        return "{" +
                "\"valor\":" + valor +
                ", \"tipoTransacao\":\"" + tipoTransacao + "\""+
                '}';
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacaoEnum getTipoTransacao() {
        return tipoTransacao;
    }
}
