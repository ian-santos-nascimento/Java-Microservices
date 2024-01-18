package br.com.microservices.java.microserviceavaliadorcredito.application.exception;

public class ComunicacaoMicroservicoException extends Exception{
    private Integer status;
    public ComunicacaoMicroservicoException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
