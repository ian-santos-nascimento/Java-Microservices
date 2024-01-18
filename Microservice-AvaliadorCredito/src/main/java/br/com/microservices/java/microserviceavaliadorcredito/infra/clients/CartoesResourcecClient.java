package br.com.microservices.java.microserviceavaliadorcredito.infra.clients;

import br.com.microservices.java.microserviceavaliadorcredito.application.domain.Cartao;
import br.com.microservices.java.microserviceavaliadorcredito.application.domain.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public  interface CartoesResourcecClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<Cartao>> getCartoesByRenda(@RequestParam("renda") Long renda);

}


