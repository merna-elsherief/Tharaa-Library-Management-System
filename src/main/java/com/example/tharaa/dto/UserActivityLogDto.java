package com.example.tharaa.dto;

import java.time.LocalDateTime;

public record UserActivityLogDto(
        Long id,
        String username,
        String action,
        String details,
        String ipAddress,
        LocalDateTime timestamp
) {}
