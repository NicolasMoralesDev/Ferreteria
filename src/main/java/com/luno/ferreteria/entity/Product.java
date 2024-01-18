/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luno.ferreteria.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


/**
 *
 * @author Nico Morales
 */
@Entity
@Data
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int price;

    private String brand;

    private int stock;
    @Column(name = "img_Url")
    private String imageUrl;
    private String status;
    @ManyToMany
    private List<SubCategory> subCategory;

    public Product() {
        this.status = "on";
    }

}
