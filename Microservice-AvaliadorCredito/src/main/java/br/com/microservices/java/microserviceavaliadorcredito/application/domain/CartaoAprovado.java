package br.com.microservices.java.microserviceavaliadorcredito.application.domain;

import java.math.BigDecimal;

public record CartaoAprovado(String cartao, String bandeira, BigDecimal limiteAprovado) {
}
