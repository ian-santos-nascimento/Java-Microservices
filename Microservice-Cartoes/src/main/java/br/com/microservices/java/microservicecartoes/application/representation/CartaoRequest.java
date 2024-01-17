package br.com.microservices.java.microservicecartoes.application.representation;

import br.com.microservices.java.microservicecartoes.domain.Cartao;
import br.com.microservices.java.microservicecartoes.domain.enums.Bandeira;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartaoRequest {

    private String nome;
    private Bandeira bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome,bandeira,renda,limite);
    }
}

