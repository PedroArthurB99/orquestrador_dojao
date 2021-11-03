package br.com.zupacademy.grupolaranja.orquestrador.estorno;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Estorno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idConta;

    @NotNull
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusEstorno statusEstorno = StatusEstorno.PENDENTE;

    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @Deprecated
    public Estorno() {
    }

    public Estorno(Long idConta, BigDecimal valor) {
        this.idConta = idConta;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Long getIdConta() {
        return idConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public void alteraStatus() {
        this.statusEstorno = StatusEstorno.REALIZADO;
    }
}
