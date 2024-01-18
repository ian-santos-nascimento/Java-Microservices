package br.com.microservices.java.microserviceavaliadorcredito.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public record SituacaoCliente(DadosCliente dadosCliente, List<CartaoCliente> cartaoClienteList) {

}
