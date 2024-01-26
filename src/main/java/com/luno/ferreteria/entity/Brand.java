package com.luno.ferreteria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBrand;
    private String title;

    public Brand(int idBrand, String title) {
        this.idBrand = idBrand;
        this.title = title;
    }

    public Brand() {

    }
}
