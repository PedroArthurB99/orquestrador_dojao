package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.extrato;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExtratoResponse {

    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoExtratoEnum operacao;

    private BigDecimal valor;

    private LocalDateTime dataTransacao;

    public Long getId() {
        return id;
    }

    public TipoExtratoEnum getOperacao() {
        return operacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }
}
