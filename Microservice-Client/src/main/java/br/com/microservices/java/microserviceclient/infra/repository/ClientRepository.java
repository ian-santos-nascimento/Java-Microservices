package br.com.microservices.java.microserviceclient.infra.repository;

import br.com.microservices.java.microserviceclient.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByCpf(String cpf);
}
