package com.simon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Oid;

    private Integer uid;

    private String username;

    private Integer pid;

    private String pname;

    private Double pprice;

    private Integer number;
}
