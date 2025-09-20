package com.example.tharaa.service;

import com.example.tharaa.domain.entity.User;
import com.example.tharaa.dto.UserActivityLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserActivityLogService {
    void logActivity(User user, String action, String details, String ipAddress);
    Page<UserActivityLogDto> getLogs(String username, String action, Pageable pageable);
}