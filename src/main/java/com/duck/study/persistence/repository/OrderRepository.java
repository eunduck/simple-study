package com.duck.study.persistence.repository;

import com.duck.study.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eunduck on 2022/11/03.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(Long userId);
}
