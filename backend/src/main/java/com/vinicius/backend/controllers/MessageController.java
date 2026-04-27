package com.vinicius.backend.controllers;

import com.vinicius.backend.entities.Message;
import com.vinicius.backend.services.MessageService;
import com.vinicius.backend.services.RateLimitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@Slf4j
public class MessageController {

    private final MessageService messageService;
    private final RateLimitService rateLimitService;

    public MessageController(MessageService messageService, RateLimitService rateLimitService) {
        this.messageService = messageService;
        this.rateLimitService = rateLimitService;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        log.info("Requisição GET /messages recebida");
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        log.info("Requisição GET /messages/{} recebida", id);
        return messageService.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createMessage(
            @Valid @RequestBody Message message,
            HttpServletRequest request) {
        try {
            log.info("Requisição POST /messages recebida de: {}", message.getEmail());

            // LOG TEMPORÁRIO - remover depois
            log.info("=== HEADERS ===");
            java.util.Collections.list(request.getHeaderNames())
                    .forEach(header -> log.info("{}: {}", header, request.getHeader(header)));
            log.info("RemoteAddr: {}", request.getRemoteAddr());
            log.info("=== FIM HEADERS ===");

            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isEmpty()) {
                ip = request.getRemoteAddr();
            }
            if (ip.contains(",")) {
                ip = ip.split(",")[0].trim();
            }

            if (!rateLimitService.isAllowed(ip, message.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.TOO_MANY_REQUESTS)
                        .body("Você já enviou uma mensagem recentemente. Tente novamente em 24 horas.");
            }

            messageService.createMessage(message);
            log.info("Mensagem criada e email enviado com sucesso!");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Mensagem enviada com sucesso!");

        } catch (Exception e) {
            log.error("Erro ao processar mensagem: {}", e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar sua mensagem. Tente novamente mais tarde.");
        }
    }
}