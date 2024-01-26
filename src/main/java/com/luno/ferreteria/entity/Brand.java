package com.luno.ferreteria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBrand;
    private String title;
    @OneToMany(fetch = FetchType.EAGER)
    private List<SubCategory> subCategory = new ArrayList<>();

    public Brand(int idBrand, String title) {
        this.idBrand = idBrand;
        this.title = title;
    }

    public Brand() {

    }
}
