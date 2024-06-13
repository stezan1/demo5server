package com.example.demo.controllers;

import com.example.demo.dto.NotificationRequestDTO;
import com.example.demo.services.FirebaseNotificationService;
import com.example.demo.services.FirebaseTokenRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class FirebaseNotificationController {

    private final FirebaseNotificationService notificationService;
    private final FirebaseTokenRetrieverService tokenRetrieverService;

    @Autowired
    public FirebaseNotificationController(FirebaseNotificationService notificationService,
                                          FirebaseTokenRetrieverService tokenRetrieverService) {
        this.notificationService = notificationService;
        this.tokenRetrieverService = tokenRetrieverService;
    }

    @PostMapping("/multicast-send-notification")
    public String multicastSendNotification(@RequestBody NotificationRequestDTO requestDTO) {
        String title = requestDTO.getTitle();
        String body = requestDTO.getBody();
        double latitude = requestDTO.getLatitude(); // Add latitude parameter
        double longitude = requestDTO.getLongitude(); // Add longitude parameter
        System.out.println("DeviceName: " + requestDTO.getDeviceName() + " is sending the request from latitude:"+requestDTO.getLatitude()+" and longitude: "+requestDTO.getLongitude());

        // Retrieve tokens asynchronously
        tokenRetrieverService.getAllTokensAsync().thenAccept(tokens -> {
            // Multicast notification to all tokens
            notificationService.multicastSendNotificationToTokens(tokens, title, body, latitude, longitude);
        });

        return "Multicast notification sent successfully.";
    }
}
