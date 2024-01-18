package br.com.microservices.java.microserviceavaliadorcredito.application.domain;

import java.math.BigDecimal;

public record Cartao(String nome, Long id, String bandeira, BigDecimal limiteBasico) {
}
