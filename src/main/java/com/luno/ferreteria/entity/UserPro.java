package com.luno.ferreteria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UserPro extends User{
    
    private Double costo;
    private Double stars;
    @OneToMany
    private List<FeedBack> feedback;
}
