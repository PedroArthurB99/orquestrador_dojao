package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.extrato;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "extrato", url = "${feign.client.api-extrato}")
public interface ExtratoClient {

    @GetMapping("/conta/{idCliente}/extrato")
    public Page<ExtratoResponse> obterExtratos(@PathVariable Long idCliente);

}
