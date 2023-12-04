package com.example.bennidemo.service;

import com.example.bennidemo.domain.Message;
import com.example.bennidemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public Optional<Message> getMessageById(Long id) {
        return this.messageRepository.findById(id);
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message save(Message message) {
        return this.messageRepository.save(message);
    }

    public boolean deleteMessageById(Long id) {
        boolean isDeleted = false;
        if (messageRepository.findById(id).isPresent()) {
            this.messageRepository.deleteById(id);
            isDeleted = true;
            return isDeleted;
        }
        return isDeleted;
    }
}
