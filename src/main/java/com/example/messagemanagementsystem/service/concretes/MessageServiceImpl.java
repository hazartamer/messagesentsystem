package com.example.messagemanagementsystem.service.concretes;

import com.example.messagemanagementsystem.entity.Message;
import com.example.messagemanagementsystem.entity.User;
import com.example.messagemanagementsystem.repository.MessageRepository;
import com.example.messagemanagementsystem.repository.UserRepository;
import com.example.messagemanagementsystem.service.abstracts.MessageService;
import com.example.messagemanagementsystem.service.dto.message.MessageListResponse;
import com.example.messagemanagementsystem.service.dto.message.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public void sentMessage(MessageRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByEmail(name);
        User receivedUser = userRepository.findById(request.getReceiverId()).orElseThrow();
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(user);
        message.setRead(false);
        message.setReceiver(receivedUser);
        message.setSentAt(LocalDateTime.now());

        messageRepository.save(message);
    }

    @Override
    public List<MessageListResponse> readMessage() {

        List<MessageListResponse> list = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByEmail(name);
        long id = user.getId();
        List<Message> message = messageRepository.findByReceiverId(id);

        for (Message m: message){
            m.setRead(true);
            messageRepository.save(m);
        }
        //messageIsReading(message);

        return message.stream().map(r -> new MessageListResponse(r.getSender().getId(),r.getContent())).toList();

    }

    public boolean messageIsReading(List<Message> message){
        for (Message m: message){
            m.setRead(true);
        }return true;
    }
    public void messageIsRead(Boolean bool){

    }
}
