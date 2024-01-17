package br.com.microservices.java.microserviceclient.application;

import br.com.microservices.java.microserviceclient.application.representation.ClientRequest;
import br.com.microservices.java.microserviceclient.domain.Client;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientRequest clientRequest){
        log.info("ENTROU AQUI");
        var client= clientRequest.toModel();
        clientService.saveClient(client);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }
    @GetMapping(params = "cpf")
    public ResponseEntity getClient(@RequestParam("cpf")  String cpf){
        var client = clientService.getByCpf(cpf);
        if(client.isPresent())
            return ResponseEntity.ok(client);
        else
            return ResponseEntity.notFound().build();
    }
}
