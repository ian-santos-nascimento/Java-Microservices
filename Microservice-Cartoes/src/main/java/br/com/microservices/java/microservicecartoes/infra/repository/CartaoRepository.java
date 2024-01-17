package br.com.microservices.java.microservicecartoes.infra.repository;

import br.com.microservices.java.microservicecartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {


    List<Cartao> findByRendaLessThanEqual(BigDecimal rendaDecimal);
}
