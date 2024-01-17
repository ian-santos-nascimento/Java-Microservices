package br.com.microservices.java.microservicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder
                .routes()
                .route(r ->
                        r.path("/clientes/**")
                            .uri("lb://MSCLIENTS"))
                .route(r ->
                        r.path("/cartoes/**")
                                .uri("lb://MSCARTOES")
                )
                .build();
    }
}
