package com.luno.ferreteria.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity

public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubCategory;
    private String title;
    @ManyToOne
    private Category category;


}
