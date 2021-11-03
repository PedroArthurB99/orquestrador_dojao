package br.com.zupacademy.grupolaranja.orquestrador.estorno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EstornoRepository extends JpaRepository<Estorno, Long> {

    List<Estorno> findByStatusEstorno(StatusEstorno statusEstorno);
}
