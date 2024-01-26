package com.luno.ferreteria.dto;

import com.luno.ferreteria.entity.Brand;
import com.luno.ferreteria.entity.SubCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private int id;

    private String name;

    private String description;

    private int price;

    private SubCategory subCategory;
//    private List<SubCategory> subCategory;

    private Brand brand;

    private String imageUrl;

    private String medida;

    private int stock;

}
