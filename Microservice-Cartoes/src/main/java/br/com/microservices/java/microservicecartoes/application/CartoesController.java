package br.com.microservices.java.microservicecartoes.application;

import br.com.microservices.java.microservicecartoes.application.representation.CartaoRequest;
import br.com.microservices.java.microservicecartoes.application.representation.CartoesClienteResponse;
import br.com.microservices.java.microservicecartoes.domain.ClienteCartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartoesController {
    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    public CartoesController(CartaoService cartaoService, ClienteCartaoService clienteCartaoService) {
        this.cartaoService = cartaoService;
        this.clienteCartaoService =clienteCartaoService;
    }


    @PostMapping
    public ResponseEntity cadastrarCartao(@RequestBody CartaoRequest cartaoRequest){
        var cartao = cartaoRequest.toModel();
        cartaoService.saveCartao(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getCartoesByRenda(@RequestParam("renda") Long renda){
        var cartoes = cartaoService.listCartoesByRenda(renda);
        return ResponseEntity.ok(cartoes);
    }
    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
         List<ClienteCartao> clienteCartoes = clienteCartaoService.listCartoesByCpf(cpf);
         List<CartoesClienteResponse> clienteResponseList = clienteCartoes.stream().map(CartoesClienteResponse::fromModel)
                 .toList();
         return ResponseEntity.ok(clienteResponseList);
    }
}
