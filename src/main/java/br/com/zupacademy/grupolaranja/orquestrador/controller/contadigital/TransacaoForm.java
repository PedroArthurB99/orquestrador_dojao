package br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.controller.boleto.BoletoForm;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransacaoForm {

    @Positive
    private Long id;

    @Positive
    private BigDecimal valor;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipoTransacaoEnum;

    public TransacaoForm(BoletoForm boletoForm) {
        this.id = boletoForm.getId();
        this.valor = boletoForm.getValor();
    }

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
