package br.com.microservices.java.microservicecartoes.infra.repository;

import br.com.microservices.java.microservicecartoes.domain.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}