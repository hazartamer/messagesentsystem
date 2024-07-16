package com.example.messagemanagementsystem.controller;

import com.example.messagemanagementsystem.service.abstracts.MessageService;
import com.example.messagemanagementsystem.service.dto.message.MessageListResponse;
import com.example.messagemanagementsystem.service.dto.message.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;

    @PostMapping("send")
    @ResponseStatus(HttpStatus.CREATED)
    public void sentMessages(@RequestBody MessageRequest request){
        service.sentMessage(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MessageListResponse> readMessage(){
       return service.readMessage();
    }
}
