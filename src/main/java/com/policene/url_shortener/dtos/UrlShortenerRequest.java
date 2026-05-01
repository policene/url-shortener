package com.policene.url_shortener.dtos;

import javax.validation.constraints.NotBlank;

public record UrlShortenerRequest(
        @NotBlank
        String url
) {
}
