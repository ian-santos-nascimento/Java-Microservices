package br.com.microservices.java.microserviceavaliadorcredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceAvaliadorCreditoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceAvaliadorCreditoApplication.class, args);
    }

}
