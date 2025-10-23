package com.linkTIC.products.application.service;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class InventoryClient {

    private final WebClient webClient;

    public InventoryClient(WebClient inventoryWebClient) {
        this.webClient = inventoryWebClient;
    }

    public Mono<JsonNode> checkInventory(String productId) {
        return webClient.get()
                .uri("/api/inventory/{id}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, 
                          resp -> Mono.error(new RuntimeException("Client Error: " + resp.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, 
                          resp -> Mono.error(new RuntimeException("Server Error: " + resp.statusCode())))
                .bodyToMono(JsonNode.class)
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(2))
                             .filter(ex -> ex instanceof IOException)
                             .onRetryExhaustedThrow((retryBackoffSpec, signal) -> signal.failure())
                );
    }
}