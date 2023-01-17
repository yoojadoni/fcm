package com.example.fcm.service.fcm;

import com.example.fcm.dto.NotificationDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class FCMService {

    public void sendAsync(NotificationDTO notificationDTO) throws InterruptedException, ExecutionException {

        Message message = Message.builder()
                .setToken(notificationDTO.getToken())
                .setWebpushConfig(WebpushConfig.builder()
                        .putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(notificationDTO.getTitle(),
                                notificationDTO.getMessage()))
                        .build())
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();

        log.info(response);
    }
}
