package com.duck.study.persistence.entity;

import com.duck.core.code.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunduck on 2022/11/03.
 *
 CREATE TABLE `order` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `order_number` char(12) NOT NULL,
 `order_status` varchar(10) NOT NULL,
 `user_id` int(11) NOT NULL,
 `created_at` datetime DEFAULT current_timestamp(),
 `updated_at` datetime DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `id_UNIQUE` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 *
 */
@Data
@Entity
@Table(name = "`order`")
public class OrderEntity extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 12)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProductEntity> productList = new ArrayList<>();

}
