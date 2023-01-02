package com.duck.study.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by eunduck on 2022/11/03.
 *
 CREATE TABLE `product` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
 `price` decimal(64,0) NOT NULL,
 `created_at` datetime DEFAULT current_timestamp(),
 `updated_at` datetime DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `id_UNIQUE` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 *
 */
@Data
@Entity
@Table(name = "product")
public class ProductEntity extends LocalDateTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column
    private BigDecimal price;
}

