package com.example.tharaa.exception;

import java.time.Instant;

public record ExceptionResponse(Instant timestamp, String message, String details) {
}
