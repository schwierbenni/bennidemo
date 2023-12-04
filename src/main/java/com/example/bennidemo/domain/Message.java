package com.example.bennidemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String messageText;
    private boolean isRead;

    private Instant timestamp;

    public Message(Long id, String name, String messageText, boolean isRead, Instant timestamp) {
        this.id = id;
        this.name = name;
        this.messageText = messageText;
        this.isRead = isRead;
        this.timestamp = Instant.now();
    }

    public Message(Long id, String name, String messageText, boolean isRead) {
        this.id = id;
        this.name = name;
        this.messageText = messageText;
        this.isRead = isRead;
    }

    public Message() {

    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + messageText + '\'' +
                ", isDone=" + isRead +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
