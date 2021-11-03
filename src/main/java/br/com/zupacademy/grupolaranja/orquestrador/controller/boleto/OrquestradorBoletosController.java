package br.com.zupacademy.grupolaranja.orquestrador.controller.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital.TransacaoForm;
import br.com.zupacademy.grupolaranja.orquestrador.exception.ObjetoErroDTO;
import br.com.zupacademy.grupolaranja.orquestrador.exception.RegraNegocioException;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto.BoletoClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto.BoletoResponse;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto.ObterBoletoRequest;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto.PagarBoletoRequest;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController("/boletos")
public class OrquestradorBoletosController {

    @Autowired
    public BoletoClient boletoClient;

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @PostMapping("/pagamento")
    @Transactional
    public void pagarBoleto(@RequestBody @Valid BoletoForm boletoForm) {
        BoletoResponse boletoResponse;
        try {
            boletoResponse = this.boletoClient.obterBoletoComValorAtualizado(new ObterBoletoRequest(boletoForm));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Não foi possível fazer o pagamento do boleto.", e.getMessage()));
        }

        this.debitar(boletoForm.getIdConta(), boletoResponse.getValor());

        try {
            this.boletoClient.pagarBoleto(new PagarBoletoRequest(boletoResponse));
        } catch (FeignException e) {
            this.estornar(boletoForm.getIdConta(), boletoResponse.getValor()); //e se der um erro aqui?
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída. Estamos estornando o valor",
                    e.getMessage()));
        }
    }

    private void debitar(Long id, BigDecimal valor) {
        try {
            contaDigitalClient.debito(id, new OperacaoRequest(valor));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }

    private void estornar(Long id, BigDecimal valor) {
        try {
            contaDigitalClient.credito(id, new OperacaoRequest(valor));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }
}
