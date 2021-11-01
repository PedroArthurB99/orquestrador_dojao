package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.recarga;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "recarga-celular", url = "${feign.client.api-recarga-celular}")
public interface RecargaCelularClient {

    @PostMapping
    public void recarregar(@RequestBody RecargaRequest recargaRequest);
}
