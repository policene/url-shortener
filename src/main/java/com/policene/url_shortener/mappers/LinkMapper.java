package com.policene.url_shortener.mappers;

import com.policene.url_shortener.dtos.LinkStatsResponseDTO;
import com.policene.url_shortener.models.Link;

import java.time.Instant;

public class LinkMapper {

    public static LinkStatsResponseDTO toStatsDTO (Link link) {

        boolean expirated = link.getExpiresAt().isBefore(Instant.now());

        return new LinkStatsResponseDTO(
                link.getSlug(),
                link.getTargetUrl(),
                link.getClickCount(),
                link.getCreatedAt(),
                link.getExpiresAt(),
                expirated
        );

    }


}
