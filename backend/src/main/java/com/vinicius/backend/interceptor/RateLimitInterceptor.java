package com.vinicius.backend.interceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    // Guarda um "balde" por IP — cada IP tem seu próprio contador de requisições
    // ConcurrentHashMap é thread-safe, ou seja, funciona corretamente com várias
    // requisições chegando ao mesmo tempo
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createBucket() {
        return Bucket.builder()
                .addLimit(
                        // classic(5, ...) → o balde comporta no máximo 5 tokens
                        // Refill.intervally(5, Duration.ofHours(1)) → a cada 1 hora
                        // o balde é reabastecido com 5 tokens de uma vez
                        // Na prática: cada IP pode enviar no máximo 5 mensagens por hora
                        Bandwidth.classic(1, Refill.intervally(1, Duration.ofHours(24)))
                )
                .build();
    }

//     Este método é executado ANTES de qualquer requisição chegar no Controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Pega o IP de quem está fazendo a requisição
        String ip = request.getRemoteAddr();

        // Se o IP já tem um balde, usa o existente
        // Se é a primeira requisição desse IP, cria um balde novo
        Bucket bucket = buckets.computeIfAbsent(ip, k -> createBucket());

        // tryConsume(1) tenta consumir 1 token do balde
        // Se ainda tem tokens disponíveis → retorna true → requisição segue normal
        // Se o balde está vazio → retorna false → requisição é bloqueada
        if (bucket.tryConsume(1)) {
            return true;
        }

        // Se chegou aqui, o IP estourou o limite
        // 429 = Too Many Requests (código HTTP padrão para rate limit)
        response.setStatus(429);
        response.getWriter().write("Muitas requisições. Tente novamente mais tarde.");
        return false;
    }
}