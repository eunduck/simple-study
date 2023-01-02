package com.duck.study.persistence.repository;

import com.duck.study.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by eunduck on 2022/10/30.
 */
@Repository
public interface CustomerUserDetailsRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
