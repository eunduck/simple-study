package com.duck.study.persistence.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by eunduck on 2022/11/03.
 *
 CREATE TABLE `logout_history` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `user_id` bigint(20) NOT NULL,
 `jwt_token` varchar(200) NOT NULL,
 `created_at` datetime DEFAULT current_timestamp(),
 PRIMARY KEY (`id`),
 UNIQUE KEY `id_UNIQUE` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 *
 */
@Data
@Entity
@Table(name = "logout_history")
public class LogoutHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private Long userId;

    private String jwtToken;

    public LogoutHistoryEntity(Long id, String token) {
        this.userId = id;
        this.jwtToken = token;
    }

    public LogoutHistoryEntity() {}
}
