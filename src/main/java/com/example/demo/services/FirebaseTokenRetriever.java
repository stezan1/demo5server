package com.example.demo.services;

import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FirebaseTokenRetriever {

    public CompletableFuture<List<String>> getAllTokensAsync() {
        DatabaseReference tokensRef = FirebaseDatabase.getInstance().getReference("FCM_Tokens");
        CompletableFuture<List<String>> futureTokens = new CompletableFuture<>();
        List<String> tokens = new ArrayList<>();
        tokensRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String token = childSnapshot.getValue(String.class);
                    tokens.add(token);
                }
                futureTokens.complete(tokens);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                futureTokens.completeExceptionally(databaseError.toException());
            }
        });

        return futureTokens;
    }
}
