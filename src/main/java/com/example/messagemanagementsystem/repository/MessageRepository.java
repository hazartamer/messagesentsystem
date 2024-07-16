package com.example.messagemanagementsystem.repository;

import com.example.messagemanagementsystem.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.isRead = false")
    List<Message> findByReceiverId(long id);
}
