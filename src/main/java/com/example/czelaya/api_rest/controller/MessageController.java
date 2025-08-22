package com.example.czelaya.api_rest.controller;


import com.example.czelaya.api_rest.components.utils.constants.APIField;
import com.example.czelaya.api_rest.entity.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(APIField.MESSAGE_API)
@Tag(name = "Message")
public class MessageController {

    private final List<Message> messages = new ArrayList<>();

    public MessageController() {
        messages.add(new Message(1, "Hola"));
        messages.add(new Message(2, "Hola 2"));
    }

    @GetMapping()
    public List<Message> getMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @PostMapping()
    public Message addMessage(@RequestBody Message message) {
        message.setId(messages.size() + 1);
        messages.add(message);
        return message;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        messages.removeIf(m -> m.getId() == id);
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        message.setId(id);
        messages.set(messages.indexOf(message), message);
        return message;
    }
}
