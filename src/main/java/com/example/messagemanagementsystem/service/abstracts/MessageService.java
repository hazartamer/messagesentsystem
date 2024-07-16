package com.example.messagemanagementsystem.service.abstracts;

import com.example.messagemanagementsystem.service.dto.message.MessageListResponse;
import com.example.messagemanagementsystem.service.dto.message.MessageRequest;

import java.util.List;

public interface MessageService {
    void sentMessage(MessageRequest request);

    List<MessageListResponse> readMessage();
}
