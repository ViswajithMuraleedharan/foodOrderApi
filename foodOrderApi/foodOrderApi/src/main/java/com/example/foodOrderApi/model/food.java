package com.example.foodOrderApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_food")
public class food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodId")
    private int foodId;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
}
