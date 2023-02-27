package com.charter.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record RewardApiError(HttpStatus status, LocalDateTime timestamp, String message, String debugMessage) {
}
