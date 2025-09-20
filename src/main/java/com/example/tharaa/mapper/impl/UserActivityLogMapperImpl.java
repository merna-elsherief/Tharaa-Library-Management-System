package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.UserActivityLog;
import com.example.tharaa.dto.UserActivityLogDto;
import com.example.tharaa.mapper.UserActivityLogMapper;
import org.springframework.stereotype.Component;

@Component
public class UserActivityLogMapperImpl implements UserActivityLogMapper {

    @Override
    public UserActivityLogDto toDto(UserActivityLog log) {
        return new UserActivityLogDto(
                log.getId(),
                log.getUser() != null ? log.getUser().getUsername() : null,
                log.getAction(),
                log.getDetails(),
                log.getIpAddress(),
                log.getTimestamp()
        );
    }
}
