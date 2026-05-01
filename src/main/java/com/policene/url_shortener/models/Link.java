package com.policene.url_shortener.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "links")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, name = "target_url")
    private String targetUrl;

    @Column(name = "click_count")
    private Integer clickCount;

    @Column(nullable = false, updatable = false, name = "created_at")
    private Instant createdAt;

    @Column(nullable = false, name = "expires_at")
    private Instant expiresAt;

    @PrePersist
    private void prePersist () {
        this.clickCount = 0;
        this.createdAt = Instant.now();
        this.expiresAt = this.createdAt.plus(7, ChronoUnit.DAYS);
    }

}
