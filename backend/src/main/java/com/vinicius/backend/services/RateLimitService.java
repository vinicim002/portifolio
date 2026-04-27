package com.vinicius.backend.services;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(2, Refill.intervally(2, Duration.ofHours(24))))
                .build();
    }

    public boolean isAllowed(String ip, String email) {
        // Chave composta IP + email — precisa mudar os dois para burlar
        String key = ip + ":" + email.toLowerCase().trim();
        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket());
        return bucket.tryConsume(1);
    }
}