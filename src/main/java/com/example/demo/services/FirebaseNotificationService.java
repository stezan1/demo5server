package com.example.demo.services;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseNotificationService {

	public void multicastSendNotificationToTokens(List<String> tokens, String notificationTitle, String notificationBody, double latitude, double longitude) {
	    MulticastMessage msg = MulticastMessage.builder()
	            .addAllTokens(tokens)
	            .setNotification(Notification.builder()
	                    .setTitle(notificationTitle)
	                    .setBody(notificationBody)
	                    .build())
	            .putData("action1", "accept")
	            .putData("action2", "reject")
	            .putData("latitude", String.valueOf(latitude))
	            .putData("longitude", String.valueOf(longitude))
	            .build();

	    try {
	        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(msg);
	        System.out.println("Notification sent to " + response.getSuccessCount() + " devices successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Failed to send multicast notification: " + e.getMessage());
	    }
	}

}
