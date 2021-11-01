package br.com.zupacademy.grupolaranja.orquestrador.controller.boleto;

import br.com.zupacademy.grupolaranja.orquestrador.controller.contadigital.TransacaoForm;
import br.com.zupacademy.grupolaranja.orquestrador.controller.recarga.RecargaForm;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto.BoletoClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.boleto.BoletoRequest;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.ContaDigitalClient;
import br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital.OperacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RestController("/boletos")
public class OrquestradorBoletosController {

    @Autowired
    public BoletoClient boletoClient;

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @PostMapping("/pagamento")
    @Transactional
    public void pagarBoleto(@RequestBody BoletoForm boletoForm) { //aguardando definição de objeto do outro time
        this.verificarSaldo(boletoForm.getId(), boletoForm.getValor()); //pegar id e valor do objeto da requisicao
        this.debitar(new TransacaoForm(boletoForm));
        this.boletoClient.pagarBoleto(new BoletoRequest(boletoForm));
    }

    private Boolean verificarSaldo(Long id, BigDecimal valor) {
        return this.contaDigitalClient.verificarSaldo(id, new OperacaoRequest(valor));
    }

    private void debitar(@RequestBody TransacaoForm operacaoForm) {
        contaDigitalClient.debito(operacaoForm.getId(), new OperacaoRequest(operacaoForm));
    }
}
