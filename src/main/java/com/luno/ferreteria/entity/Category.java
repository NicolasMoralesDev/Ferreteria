/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luno.ferreteria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


/**
 *
 * @author Nico Morales
 */

@Data
@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;
    private String title;
    @ManyToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<SubCategory> subCategory;

    public Category(int idCategory, String title) {
        this.idCategory = idCategory;
        this.title = title;

    }

    public Category() {

    }
}
