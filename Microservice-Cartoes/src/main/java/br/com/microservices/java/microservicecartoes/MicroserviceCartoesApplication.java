package br.com.microservices.java.microservicecartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MicroserviceCartoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCartoesApplication.class, args);
    }

}
