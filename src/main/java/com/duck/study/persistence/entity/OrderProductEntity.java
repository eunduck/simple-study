package com.duck.study.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by eunduck on 2022/11/03.
 *
 CREATE TABLE `order_product` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `order_id` int(11) NOT NULL,
 `product_id` int(11) NOT NULL,
 `order_price` decimal(64,0) NOT NULL,
 `quantity` int(3) NOT NULL,
 `created_at` datetime DEFAULT current_timestamp(),
 `updated_at` datetime DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `id_UNIQUE` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 *
 */
@Data
@Entity
@Table(name = "order_product")
public class OrderProductEntity extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private BigDecimal orderPrice;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "product_id")
    private ProductEntity product;
}
