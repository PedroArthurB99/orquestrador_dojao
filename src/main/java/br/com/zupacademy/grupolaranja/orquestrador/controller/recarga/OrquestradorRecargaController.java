package br.com.zupacademy.grupolaranja.orquestrador.controller.recarga;

import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.recarga.RecargaCelularClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.recarga.RecargaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/recarga")
public class OrquestradorRecargaController {

    @Autowired
    private RecargaCelularClient recargaCelularClient;

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @PostMapping
    public void recarregar(@RequestBody RecargaForm recargaForm) {
        verificarSaldo(recargaForm);
        //primeiro vai debitar
        recargaCelularClient.recarregar(new RecargaRequest(recargaForm));
    }

    private Boolean verificarSaldo(RecargaForm recargaForm) {
        return this.contaDigitalClient.verificarSaldo(recargaForm.getId(), new OperacaoRequest(recargaForm.getValor()));
    }
}
