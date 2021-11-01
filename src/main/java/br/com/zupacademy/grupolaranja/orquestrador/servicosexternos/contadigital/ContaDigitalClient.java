package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "contas-digitais", url = "${feign.client.api-conta-digital}")
public interface ContaDigitalClient {

    @PostMapping("/{id}/credito")
    public String credito(@PathVariable Long id, @RequestBody OperacaoRequest operacaoRequest);

    @PostMapping("/{id}/debito")
    public String debito(@PathVariable Long id, @RequestBody OperacaoRequest operacaoRequest);

    @PostMapping("/{id}/verifica-saldo")
    public Boolean verificarSaldo(@PathVariable Long id, @RequestBody OperacaoRequest operacaoRequest);
}
