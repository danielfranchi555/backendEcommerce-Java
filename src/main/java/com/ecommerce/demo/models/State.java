package com.ecommerce.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_state;
    private String name_state;

    @OneToMany(mappedBy = "order_state")
    private List<Orders> orders;
}
