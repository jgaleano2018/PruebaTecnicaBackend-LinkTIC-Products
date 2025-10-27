package com.linkTIC.products.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.netty.channel.ChannelOption;

//import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient inventoryWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081") // Inventory microservice
                //.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader("X-API-KEY", "my-secure-api-key")
                //.defaultHeaders(headers -> headers.setBasicAuth("product-service", "secret-password"))
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                  .responseTimeout(Duration.ofSeconds(10))
                                  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                ))
                .build();
    }
}