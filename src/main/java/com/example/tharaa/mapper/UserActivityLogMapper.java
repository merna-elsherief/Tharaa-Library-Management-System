package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.UserActivityLog;
import com.example.tharaa.dto.UserActivityLogDto;

public interface UserActivityLogMapper {
    UserActivityLogDto toDto(UserActivityLog log);
}
