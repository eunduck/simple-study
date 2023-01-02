package com.duck.study.service;

import com.duck.study.persistence.entity.LogoutHistoryEntity;
import com.duck.study.persistence.repository.LogoutHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by eunduck on 2022/11/03.
 */
@Service
@RequiredArgsConstructor
public class LogoutService {
    private final LogoutHistoryRepository logoutHistoryRepository;

    public void logout(Long userId, String token) {
        LogoutHistoryEntity entity = new LogoutHistoryEntity(userId, token);
        logoutHistoryRepository.save(entity);
    }

    public boolean logoutBefore(String token) {
        return logoutHistoryRepository.findByJwtToken(token).isPresent();
    }
}
