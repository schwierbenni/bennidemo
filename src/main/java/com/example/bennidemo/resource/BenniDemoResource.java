package com.example.bennidemo.resource;

import com.example.bennidemo.domain.Message;
import com.example.bennidemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class BenniDemoResource {

    @Autowired
    private MessageService messageService;

    @RequestMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = this.messageService.getMessageById(id);
        if (message.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        final Message save = this.messageService.save(message);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(save);
    }

    @PutMapping("{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        final Optional<Message> optionalMessage = this.messageService.getMessageById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setMessageText(updatedMessage.getMessageText());
            message.setName(updatedMessage.getName());
            message.setRead(updatedMessage.isRead());
            Message savedMessage = this.messageService.save(message);
            return new ResponseEntity<>(savedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping()
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> allMessages = this.messageService.getAllMessages();
        return new ResponseEntity<>(allMessages, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        boolean isDeleted = this.messageService.deleteMessageById(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
