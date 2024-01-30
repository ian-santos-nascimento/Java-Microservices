package br.com.microservices.java.microserviceavaliadorcredito.application;

import br.com.microservices.java.microserviceavaliadorcredito.ClienteResourceClient;
import br.com.microservices.java.microserviceavaliadorcredito.application.domain.*;
import br.com.microservices.java.microserviceavaliadorcredito.application.exception.ComunicacaoMicroservicoException;
import br.com.microservices.java.microserviceavaliadorcredito.application.exception.DadosClienteNotFoundException;
import br.com.microservices.java.microserviceavaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import br.com.microservices.java.microserviceavaliadorcredito.infra.clients.CartoesResourcecClient;
import br.com.microservices.java.microserviceavaliadorcredito.infra.producer.SolicitacaoEmissaoCartaoPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient resourceClient;
    private final CartoesResourcecClient cartoesResourcecClient;
    private final SolicitacaoEmissaoCartaoPublisher cartaoPublisher;


    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ComunicacaoMicroservicoException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = resourceClient.getClient(cpf);
            ResponseEntity<List<CartaoCliente>> cartaoCliente = cartoesResourcecClient.getCartoesByCliente(cpf);
            return SituacaoCliente.builder()
                    .dadosCliente(dadosCliente.getBody())
                    .cartaoClienteList(cartaoCliente.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ComunicacaoMicroservicoException(e.getMessage(), status);

        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ComunicacaoMicroservicoException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = resourceClient.getClient(cpf);
            ResponseEntity<List<Cartao>> cartoes = cartoesResourcecClient.getCartoesByRenda(renda);
            List<CartaoAprovado> cartaoList = !cartoes.getBody().isEmpty() || cartoes.getBody() != null ? cartoes.getBody().stream().map(cartao -> {
                BigDecimal calculoLimite =BigDecimal.valueOf(dadosCliente.getBody().idade()).divide(BigDecimal.TEN).multiply(BigDecimal.valueOf(0.5));
                BigDecimal limiteAprovado = cartao.limite().multiply(calculoLimite);
                return new CartaoAprovado(cartao.nome(), cartao.bandeira(), limiteAprovado);
            }).toList() : new ArrayList<>();
            return new RetornoAvaliacaoCliente(cartaoList);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ComunicacaoMicroservicoException(e.getMessage(), status);

        }
    }

    public  ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao){
        try {
            cartaoPublisher.solicitarCartao(dadosSolicitacaoEmissaoCartao);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (JsonProcessingException e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
