package com.policene.url_shortener.dtos;


import jakarta.validation.constraints.NotBlank;

public record UrlShortenerRequestDTO(
        @NotBlank(message = "URL can't be blank.")
        String url
) {
}
    