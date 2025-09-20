package com.example.tharaa.controller;

import com.example.tharaa.dto.UserActivityLogDto;
import com.example.tharaa.service.UserActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/logs")
@RequiredArgsConstructor
public class UserActivityLogController {

    private final UserActivityLogService logService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<UserActivityLogDto> getLogs(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String action,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return logService.getLogs(username, action, PageRequest.of(page, size));
    }
}
