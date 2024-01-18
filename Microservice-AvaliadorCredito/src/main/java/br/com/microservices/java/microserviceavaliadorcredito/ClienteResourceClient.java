package br.com.microservices.java.microserviceavaliadorcredito;

import br.com.microservices.java.microserviceavaliadorcredito.application.domain.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msclients", path = "/clientes")
public interface ClienteResourceClient {
    @GetMapping(params = "cpf")
    public ResponseEntity<DadosCliente> getClient(@RequestParam("cpf")  String cpf);

}
