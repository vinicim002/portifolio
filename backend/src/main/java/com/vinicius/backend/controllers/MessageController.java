package com.vinicius.backend.controllers;

import com.vinicius.backend.entities.Message;
import com.vinicius.backend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //Get /messages - Listar todos
    @GetMapping
    public ResponseEntity<List> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    //Get /messages/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        return messageService.getMessagelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Post /messages - Criar nova message
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createMessage = messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(createMessage);
    }
}
