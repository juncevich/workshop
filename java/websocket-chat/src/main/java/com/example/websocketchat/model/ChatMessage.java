package com.example.websocketchat.model;

import lombok.Data;

@Data
public class ChatMessage {
    private MessageType messageType;
    private String content;
    private String sender;
}
