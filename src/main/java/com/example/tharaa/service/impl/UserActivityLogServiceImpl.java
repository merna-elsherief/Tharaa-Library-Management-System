package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.User;
import com.example.tharaa.domain.entity.UserActivityLog;
import com.example.tharaa.dto.UserActivityLogDto;
import com.example.tharaa.repository.UserActivityLogRepository;
import com.example.tharaa.service.UserActivityLogService;
import com.example.tharaa.mapper.UserActivityLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivityLogServiceImpl implements UserActivityLogService {

    private final UserActivityLogRepository repository;
    private final UserActivityLogMapper mapper;

    @Override
    public void logActivity(User user, String action, String details, String ipAddress) {
        UserActivityLog log = UserActivityLog.builder()
                .user(user)
                .action(action)
                .details(details)
                .ipAddress(ipAddress)
                .timestamp(LocalDateTime.now())
                .build();
        repository.save(log);
    }

    @Override
    public Page<UserActivityLogDto> getLogs(String username, String action, Pageable pageable) {
        Page<UserActivityLog> logs;

        if (username != null && !username.isEmpty()) {
            logs = repository.findByUser_UsernameContainingIgnoreCase(username, pageable);
        } else if (action != null && !action.isEmpty()) {
            logs = repository.findByActionContainingIgnoreCase(action, pageable);
        } else {
            logs = repository.findAll(pageable);
        }

        return logs.map(mapper::toDto); // 👈 استدعاء instance method
    }
}
