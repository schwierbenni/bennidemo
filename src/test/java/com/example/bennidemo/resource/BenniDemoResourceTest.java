package com.example.bennidemo.resource;

import com.example.bennidemo.domain.Message;
import com.example.bennidemo.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BenniDemoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        // Initialize your database with test data if needed.
    }

    @Test
    public void testGetMessageById() throws Exception {
        // Insert a test message into the database
        Message message = new Message();
        message.setMessageText("Test Message");
        message.setName("John");
        message.setRead(false);
        message = messageService.save(message);

        Long messageId = message.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/messages/{id}", messageId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(messageId));
    }

    @Test
    public void testsave() throws Exception {
        // Create a JSON representation of a message
        String messageJson = "{\"messageText\":\"Test Message\",\"name\":\"John\",\"read\":false}";

        mockMvc.perform(MockMvcRequestBuilders.post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(messageJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Verify that the message was created in the database
        List<Message> messages = messageService.getAllMessages();
        org.assertj.core.api.Assertions.assertThat(messages).hasSize(1);
    }

    @Test
    public void testUpdateMessage() throws Exception {
        // Insert a test message into the database
        Message message = new Message();
        message.setMessageText("Test Message");
        message.setName("John");
        message.setRead(false);
        message = messageService.save(message);

        Long messageId = message.getId();

        // Create a JSON representation of an updated message
        String updatedMessageJson = "{\"messageText\":\"Updated Message\",\"name\":\"Jane\",\"read\":true}";

        mockMvc.perform(MockMvcRequestBuilders.put("/messages/{id}", messageId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedMessageJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageText").value("Updated Message"));
    }

    @Test
    public void testGetAllMessages() throws Exception {
        // Insert some test messages into the database
        Message message1 = new Message();
        message1.setMessageText("Message 1");
        message1.setName("John");
        message1.setRead(false);
        messageService.save(message1);

        Message message2 = new Message();
        message2.setMessageText("Message 2");
        message2.setName("Jane");
        message2.setRead(true);
        messageService.save(message2);

        mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void testDeleteMessage() throws Exception {
        // Insert a test message into the database
        Message message = new Message();
        message.setMessageText("Test Message");
        message.setName("John");
        message.setRead(false);
        message = messageService.save(message);

        Long messageId = message.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/messages/{id}", messageId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Verify that the message was deleted from the database
        List<Message> messages = messageService.getAllMessages();
        org.assertj.core.api.Assertions.assertThat(messages).isEmpty();
    }

}