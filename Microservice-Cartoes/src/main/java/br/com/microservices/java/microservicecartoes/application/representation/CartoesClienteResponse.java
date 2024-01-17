package br.com.microservices.java.microservicecartoes.application.representation;

import br.com.microservices.java.microservicecartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesClienteResponse fromModel(ClienteCartao clienteCartao){
        return new CartoesClienteResponse(clienteCartao.getCartao().getNome(),
                clienteCartao.getCartao().getBandeira().toString(),
                clienteCartao.getLimite());
    }
}
