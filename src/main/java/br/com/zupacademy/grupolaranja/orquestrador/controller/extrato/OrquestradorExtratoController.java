package br.com.zupacademy.grupolaranja.orquestrador.controller.extrato;

import br.com.zupacademy.grupolaranja.orquestrador.exception.ObjetoErroDTO;
import br.com.zupacademy.grupolaranja.orquestrador.exception.RegraNegocioException;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.extrato.ExtratoClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.extrato.ExtratoResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/extrato")
public class OrquestradorExtratoController {

    @Autowired
    private ExtratoClient extratoClient;

    @GetMapping("/{idConta}")
    public Page<ExtratoResponse> listarExtratos(@PathVariable Long idConta) {
        return this.chamarApiExterna(idConta);
    }

    private Page<ExtratoResponse> chamarApiExterna(Long id) {
        try {
            return extratoClient.obterExtratos(id);
        } catch (FeignException e) {
            throw new RegraNegocioException(new ObjetoErroDTO("Não foi possível gerar o seu extrato.", e.getMessage()));
        }
    }
}
