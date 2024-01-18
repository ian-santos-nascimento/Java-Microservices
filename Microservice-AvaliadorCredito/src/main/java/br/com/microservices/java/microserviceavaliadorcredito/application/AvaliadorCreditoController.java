package br.com.microservices.java.microserviceavaliadorcredito.application;

import br.com.microservices.java.microserviceavaliadorcredito.application.domain.DadosAvaliacao;
import br.com.microservices.java.microserviceavaliadorcredito.application.domain.RetornoAvaliacaoCliente;
import br.com.microservices.java.microserviceavaliadorcredito.application.domain.SituacaoCliente;
import br.com.microservices.java.microserviceavaliadorcredito.application.exception.ComunicacaoMicroservicoException;
import br.com.microservices.java.microserviceavaliadorcredito.application.exception.DadosClienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    public AvaliadorCreditoController(AvaliadorCreditoService avaliadorCreditoService) {
        this.avaliadorCreditoService = avaliadorCreditoService;
    }


    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(params ="cpf", value = "situacao-cliente")
    public ResponseEntity consultarSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = null;
        try {
            situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ComunicacaoMicroservicoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
        return ResponseEntity.ok(situacaoCliente);
    }
    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao){
        RetornoAvaliacaoCliente retornoAvaliacaoCliente = null;
        try {
            retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dadosAvaliacao.cpf(), dadosAvaliacao.renda());
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ComunicacaoMicroservicoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
        return ResponseEntity.ok(retornoAvaliacaoCliente);
    }
}
