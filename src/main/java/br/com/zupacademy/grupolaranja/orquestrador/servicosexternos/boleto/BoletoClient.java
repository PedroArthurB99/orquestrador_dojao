package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "boletos", url = "${feign.client.api-boletos}")
public interface BoletoClient {

    @PostMapping("/pagamento")
    public void pagarBoleto(@RequestBody PagarBoletoRequest boletoRequest); //Aguardando definição do outro time

    @GetMapping("/valor-atualizado")
    public BoletoResponse obterBoletoComValorAtualizado(@RequestBody ObterBoletoRequest boletoRequest);

}
