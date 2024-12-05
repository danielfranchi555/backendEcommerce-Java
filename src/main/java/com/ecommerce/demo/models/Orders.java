package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_order;
    private Date order_date;
    private double total;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id_state")
    private State order_state;

    @OneToOne(mappedBy = "order")
    private OrderDetail orderDetail;
}
