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
                .addLimit(Bandwidth.classic(1, Refill.intervally(1, Duration.ofHours(24))))
                .build();
    }

    public boolean isAllowed(String ip) {
        Bucket bucket = buckets.computeIfAbsent(ip, k -> createBucket());
        return bucket.tryConsume(1);
    }
}