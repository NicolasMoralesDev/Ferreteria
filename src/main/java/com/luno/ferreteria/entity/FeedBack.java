package com.luno.ferreteria.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double star;
    private String observation;
    @ManyToOne
    private User usuario;
}
