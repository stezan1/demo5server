package com.example.demo.controllers;

import com.example.demo.services.FirebaseTokenRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class FirebaseTokenRetrieverController {

    private final FirebaseTokenRetrieverService tokenRetrieverService;

    @Autowired
    public FirebaseTokenRetrieverController(FirebaseTokenRetrieverService tokenRetrieverService) {
        this.tokenRetrieverService = tokenRetrieverService;
    }

    @GetMapping("/get-all-tokens")
    public List<String> getAllTokens() {
        return (List<String>) tokenRetrieverService.getAllTokensAsync();
    }
}
