package br.com.microservices.java.microserviceclient.application;

import br.com.microservices.java.microserviceclient.domain.Client;
import br.com.microservices.java.microserviceclient.infra.repository.ClientRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    @Transactional
    public Client saveClient(Client client){
        return repository.save(client);
    }

    public Optional<Client> getByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
