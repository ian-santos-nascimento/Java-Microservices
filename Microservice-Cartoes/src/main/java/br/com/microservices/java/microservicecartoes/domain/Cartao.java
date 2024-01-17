package br.com.microservices.java.microservicecartoes.domain;

import br.com.microservices.java.microservicecartoes.domain.enums.Bandeira;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao(String nome, Bandeira bandeira, BigDecimal renda, BigDecimal limite) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limite = limite;
    }
}
