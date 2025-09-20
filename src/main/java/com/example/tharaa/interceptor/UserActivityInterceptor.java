package com.example.tharaa.interceptor;

import com.example.tharaa.domain.entity.User;
import com.example.tharaa.service.UserActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class UserActivityInterceptor implements HandlerInterceptor {

    private final UserActivityLogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof User user) {
            String action = request.getMethod() + " " + request.getRequestURI();
            String ip = request.getRemoteAddr();

            logService.logActivity(
                    user,
                    action,
                    "Request handled",
                    ip
            );
        }

        return true; // لازم نرجع true عشان الريكوست يكمل
    }
}
