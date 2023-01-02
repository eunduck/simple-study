package com.duck.study.persistence.repository;

import com.duck.study.persistence.entity.LogoutHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by eunduck on 2022/11/03.
 */
@Repository
public interface LogoutHistoryRepository extends JpaRepository<LogoutHistoryEntity, String> {
    Optional<LogoutHistoryEntity> findByJwtToken(String token);
}
