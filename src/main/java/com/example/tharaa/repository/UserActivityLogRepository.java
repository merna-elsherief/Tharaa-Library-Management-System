package com.example.tharaa.repository;

import com.example.tharaa.domain.entity.UserActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, Long> {

    Page<UserActivityLog> findByUser_UsernameContainingIgnoreCase(String username, Pageable pageable);

    Page<UserActivityLog> findByActionContainingIgnoreCase(String action, Pageable pageable);
}
