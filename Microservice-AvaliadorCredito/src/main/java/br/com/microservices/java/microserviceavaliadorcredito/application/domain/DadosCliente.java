package br.com.microservices.java.microserviceavaliadorcredito.application.domain;

import lombok.Data;

public record DadosCliente(Long id, String cpf, Integer idade, String nome) {

}
