package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_orderDetail;
    private int quantity;
    private double unit_price;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id_product")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id_order")
    private Orders order;
}
