package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/** RDS 연결 증명용 최소 엔티티 — /db 호출마다 한 행씩 쌓인다. */
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
