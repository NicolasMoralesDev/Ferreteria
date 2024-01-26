package com.luno.ferreteria.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubCategory;
    private String title;


}
