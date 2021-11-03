package br.com.zupacademy.grupolaranja.orquestrador.servicosexternos.contadigital;

import br.com.zupacademy.grupolaranja.orquestrador.estorno.Estorno;
import br.com.zupacademy.grupolaranja.orquestrador.estorno.EstornoRepository;
import br.com.zupacademy.grupolaranja.orquestrador.estorno.StatusEstorno;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RealizarOperacaoEstorno {

    @Autowired
    private EstornoRepository repository;

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @Scheduled(fixedDelayString = "${estorno-timer}")
    public void associarCartao() {
        List<Estorno> propostas = this.repository.findByStatusEstorno(StatusEstorno.PENDENTE);
        propostas.forEach(estorno -> {
            try {
                OperacaoRequest operacaoRequest = new OperacaoRequest(estorno.getValor());
                String solicitacao = this.contaDigitalClient.credito(estorno.getIdConta(), operacaoRequest);
                estorno.alteraStatus();
                repository.save(estorno);
            } catch (FeignException exception) {
                System.out.println("Deu errado, " + exception.getMessage());
            }

        });
    }
}
