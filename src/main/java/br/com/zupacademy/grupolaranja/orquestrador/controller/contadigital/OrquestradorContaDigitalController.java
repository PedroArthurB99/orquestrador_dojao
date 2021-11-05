package br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.exception.ObjetoErroDTO;
import br.com.zupacademy.grupolaranja.orquestrador.exception.RegraNegocioException;
import br.com.zupacademy.grupolaranja.orquestrador.service.EmailTopicProducer;
import br.com.zupacademy.grupolaranja.orquestrador.service.ExtratoTopicProducer;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/contas_digitais")
public class OrquestradorContaDigitalController {

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @Autowired
    private EmailTopicProducer emailTopicProducer;

    @Autowired
    private ExtratoTopicProducer extratoTopicProducer;

    @PostMapping("/transacao")
    @Transactional
    public void transacao(@RequestBody @Valid TransacaoForm operacaoForm) {
        System.out.println("teste");
        EstornoDto estornoDto;
        EmaiDto emailDto;
        if (operacaoForm.getTipoTransacaoEnum().equals(TipoTransacaoEnum.DEBITAR)) {
            this.debitar(operacaoForm);
            estornoDto = new EstornoDto(operacaoForm.getValor(), TipoTransacaoEnum.DEBITAR);
            emailDto = new EmaiDto("user@com.br", "Voce realizou uma operação de débito no valor de "+operacaoForm.getValor());
            this.alimentarTopicos(estornoDto, emailDto);

        }
        else {
            this.creditar(operacaoForm);
            estornoDto = new EstornoDto(operacaoForm.getValor(), TipoTransacaoEnum.CREDITAR);
            emailDto = new EmaiDto("user@com.br", "Voce realizou uma operação de crédito no valor de "+operacaoForm.getValor());
            this.alimentarTopicos(estornoDto, emailDto);
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

    private void alimentarTopicos(EstornoDto estornoDto, EmaiDto email) {
        extratoTopicProducer.send(estornoDto.toString());
        emailTopicProducer.send(email.toString());
    }
}
