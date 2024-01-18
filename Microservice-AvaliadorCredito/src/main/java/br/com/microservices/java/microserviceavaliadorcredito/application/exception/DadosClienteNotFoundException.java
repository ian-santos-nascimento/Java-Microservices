package br.com.microservices.java.microserviceavaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends Exception{
    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrado para o CPF informado");

    }
}
