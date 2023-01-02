package com.duck.study.persistence.entity;

import com.duck.core.code.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunduck on 2022/10/28.
 *
 CREATE TABLE `user` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(60) NOT NULL,
 `nickname` varchar(30) NOT NULL,
 `password` varchar(100) NOT NULL,
 `phone` varchar(20) NOT NULL,
 `email` varchar(100) NOT NULL,
 `gender` char(1) DEFAULT NULL,
 `created_at` datetime DEFAULT current_timestamp(),
 `updated_at` datetime DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `id_UNIQUE` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 *
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 60)
    private String name;

    @Column(length = 30)
    private String nickname;

    @Column(length = 100)
    private String password;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orderList = new ArrayList<>();
}
