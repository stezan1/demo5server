package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FirebaseTokenRetrieverService {

    private final FirebaseTokenRetriever tokenRetriever;

    public FirebaseTokenRetrieverService(FirebaseTokenRetriever tokenRetriever) {
        this.tokenRetriever = tokenRetriever;
    }

    public CompletableFuture<List<String>> getAllTokensAsync() {
        return tokenRetriever.getAllTokensAsync();
    }
}
