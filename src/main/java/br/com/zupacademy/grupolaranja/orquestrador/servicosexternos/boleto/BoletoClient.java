package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.extrato.ExtratoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "boletos", url = "${feign.client.api-boletos}")
public interface BoletoClient {

    @PatchMapping("/pagamentos/{codigoDeBarras/confirmar")
    public void pagarBoleto(@PathVariable String codigoDeBarras);

    @PostMapping("/pagamentos/valorTotal")
    public BoletoResponse obterBoletoComValorAtualizado(@RequestBody ObterBoletoRequest boletoRequest);

    @GetMapping("/pagamentos/periodo")
    public Page<ExtratoResponse> obterPagamentosPorPeriodo(@RequestParam(defaultValue = "") Long clientId,
                                                           @RequestParam(defaultValue = "") String inicio,
                                                           @RequestParam(defaultValue = "") Long termino);
}
