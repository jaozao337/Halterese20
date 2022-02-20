package com.solagna.haltere_se20.Helper;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificacaoService extends FirebaseMessagingService {
    private static final String TAG = "TAG";
    private DatabaseReference bancoDeDados;


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Token");
        String token = FirebaseMessaging.getInstance().getToken().toString();
        System.out.println("ssssssssssssssssssssss");
        System.out.println("Token name is:" + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

}