package br.com.microservices.java.microservicesjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicesJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesJavaApplication.class, args);
	}

}
