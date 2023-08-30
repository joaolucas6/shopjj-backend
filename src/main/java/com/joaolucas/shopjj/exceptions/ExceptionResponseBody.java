package com.joaolucas.shopjj.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponseBody(String error, Integer errorCode, String message, LocalDateTime timestamp) {
}
