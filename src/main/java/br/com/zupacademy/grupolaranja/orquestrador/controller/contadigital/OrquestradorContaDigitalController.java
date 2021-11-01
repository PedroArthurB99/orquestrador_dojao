package br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController("/contas_digitais")
public class OrquestradorContaDigitalController {

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @PostMapping("/transacao")
    @Transactional
    public void transacao(@RequestBody TransacaoForm operacaoForm) {
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
        contaDigitalClient.debito(operacaoForm.getId(), new OperacaoRequest(operacaoForm));
    }

    private void creditar(@RequestBody TransacaoForm operacaoForm) {
        contaDigitalClient.credito(operacaoForm.getId(), new OperacaoRequest(operacaoForm));
    }

    private void alimentarTopicos() {}
}
