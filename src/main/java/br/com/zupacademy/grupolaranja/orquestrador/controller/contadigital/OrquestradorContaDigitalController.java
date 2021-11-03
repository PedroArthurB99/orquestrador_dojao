package br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.exception.ObjetoErroDTO;
import br.com.zupacademy.grupolaranja.orquestrador.exception.RegraNegocioException;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController("/contas_digitais")
public class OrquestradorContaDigitalController {

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @PostMapping("/transacao")
    @Transactional
    public void transacao(@RequestBody @Valid TransacaoForm operacaoForm) {
        if (operacaoForm.getTipoTransacaoEnum().equals(TipoTransacaoEnum.DEBITAR)) {
            this.debitar(operacaoForm);
            this.alimentarTopicos();
        }
        else {
            this.creditar(operacaoForm);
            this.alimentarTopicos();
        }
    }

    private void debitar(@RequestBody TransacaoForm operacaoForm) {
        try {
            contaDigitalClient.debito(operacaoForm.getId(), new OperacaoRequest(operacaoForm));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }

    private void creditar(@RequestBody TransacaoForm operacaoForm) {
        try {
            contaDigitalClient.credito(operacaoForm.getId(), new OperacaoRequest(operacaoForm));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }

    private void alimentarTopicos() {
        //preencher aqui a alimentação do kafka com os topicos de extrato e transação
    }
}
