package com.policene.url_shortener.dtos;

import javax.validation.constraints.NotBlank;

public record UrlShortenerRequestDTO(
        @NotBlank
        String url
) {
}
