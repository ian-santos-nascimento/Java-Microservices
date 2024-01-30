package br.com.microservices.java.microserviceavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartaoQueue;
    @Bean
    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartaoQueue,true);
    }

}
