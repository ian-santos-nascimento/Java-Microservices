package br.com.microservices.java.microserviceavaliadorcredito.application.domain;

import lombok.Data;

import java.math.BigDecimal;

public record CartaoCliente(String nome, String  bandeira, BigDecimal limiteLiberado) {

}
