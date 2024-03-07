package com.luno.ferreteria.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UserPro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String urlImg;
    private String nombre;
    private String email;
    @OneToOne
    private User user;
    @Nullable
    private Double costo;
    private int stars;
    private int cantFeedBack;
    @OneToMany
    private List<FeedBack> feedback;
}
