package br.com.microservices.java.microserviceclient.application.representation;

import br.com.microservices.java.microserviceclient.domain.Client;
import lombok.Data;

@Data
public class ClientRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Client toModel(){
        return new Client(nome,cpf,idade);
    }
}
