package com.vinicius.backend.services;

import com.vinicius.backend.entities.Message;
import com.vinicius.backend.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    //Post - criar nova message
    public Message createMessage(Message message) {
        if (message.getName() == null || message.getName().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (message.getEmail() == null || message.getEmail().isBlank()) {
            throw new IllegalArgumentException("O email é obrigatório.");
        }

        if (message.getSubject() == null || message.getSubject().isBlank()) {
            throw new IllegalArgumentException("O assuto é obrigatório.");
        }
        
        if (message.getBody() == null || message.getBody().isBlank()) {
            throw new IllegalArgumentException("A mensagem é obrigatória.");
        }
        return messageRepository.save(message);
    }
    // Get - Consultar todas as mensagens
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    //Get - Buscar por ID
    public Optional<Message> getMessagelById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message;
        }
        throw new RuntimeException("Message não encontrada com ID: " + id);
    }
}