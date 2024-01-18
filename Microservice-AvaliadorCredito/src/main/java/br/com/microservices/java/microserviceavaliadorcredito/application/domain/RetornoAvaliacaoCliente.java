package br.com.microservices.java.microserviceavaliadorcredito.application.domain;

import java.util.List;

public record RetornoAvaliacaoCliente(List<CartaoAprovado> cartoes) {
}
