package br.com.microservices.java.microservicecartoes.infra.subscriber;

import br.com.microservices.java.microservicecartoes.domain.Cartao;
import br.com.microservices.java.microservicecartoes.domain.ClienteCartao;
import br.com.microservices.java.microservicecartoes.domain.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.java.microservicecartoes.infra.repository.CartaoRepository;
import br.com.microservices.java.microservicecartoes.infra.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;


    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload){
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("PAYLOAD RABBITMQ:"+ payload);
            var mensagem = mapper.readValue(payload,DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(mensagem.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = ClienteCartao.builder()
                    .cartao(cartao)
                    .limite(mensagem.getLimiteLiberado())
                    .cpf(mensagem.getCpf())
                    .build();
            clienteCartaoRepository.save(clienteCartao);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info(payload);
    }

}
