package br.com.microservices.java.microservicecartoes.application;

import br.com.microservices.java.microservicecartoes.domain.ClienteCartao;
import br.com.microservices.java.microservicecartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private  final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

}
