package br.com.zupacademy.grupolaranja.orquestrador.controller.recarga;

import br.com.zupacademy.grupolaranja.orquestrador.exception.ObjetoErroDTO;
import br.com.zupacademy.grupolaranja.orquestrador.exception.RegraNegocioException;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.recarga.RecargaCelularClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.recarga.RecargaRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/recarga")
public class OrquestradorRecargaController {

    @Autowired
    private RecargaCelularClient recargaCelularClient;

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @PostMapping
    @Transactional
    public void recarregar(@RequestBody @Valid RecargaForm recargaForm) {
        this.debitar(recargaForm);
        try {
            recargaCelularClient.recarregar(new RecargaRequest(recargaForm));
        } catch (FeignException e) {
            this.creditar(recargaForm);
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }

    private void debitar(RecargaForm recargaForm) {
        try {
            this.contaDigitalClient.debito(recargaForm.getId(), new OperacaoRequest(recargaForm.getValor()));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }

    private void creditar(RecargaForm recargaForm) {
        try {
            this.contaDigitalClient.credito(recargaForm.getId(), new OperacaoRequest(recargaForm.getValor()));
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Sua transação não foi concluída.", e.getMessage()));
        }
    }
}
