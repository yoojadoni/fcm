package com.example.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationDTO {
    private String title;
    private String message;
    private String token;

    @Builder
    public NotificationDTO(String title, String message, String token) {
        this.title = title;
        this.message = message;
        this.token = token;
    }
}
