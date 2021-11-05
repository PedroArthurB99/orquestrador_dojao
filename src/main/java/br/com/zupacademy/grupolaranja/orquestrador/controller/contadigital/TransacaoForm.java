package br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransacaoForm {

    @Positive
    private Long id;

    @Positive
    private BigDecimal valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipoTransacaoEnum;

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacaoEnum getTipoTransacaoEnum() {
        return tipoTransacaoEnum;
    }
}
