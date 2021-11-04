package br.com.zupacademy.grupolaranja.orquestrador.controller.boleto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class BoletoForm {

    @Positive
    private Long idConta;

    @NotBlank
    private String codigoDeBarras;

    @NotBlank
    private String emailCliente;

    public Long getIdConta() {
        return idConta;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public String getEmailCliente() {
        return emailCliente;
    }
}
