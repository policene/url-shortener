package com.policene.url_shortener.dtos;

import java.time.Instant;

public record LinkStatsResponseDTO(
        String slug,
        String targetUrl,
        Integer clickCount,
        Instant createdAt,
        Instant expiratesAt,
        boolean expirated
) {
}
