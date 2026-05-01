package com.policene.url_shortener.exceptions;

import java.time.Instant;

public record ErrorResponse(
        String httpMessage,
        int httpStatus,
        String errorMessage,
        Instant timeStamp
) {
}
