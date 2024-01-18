package com.luno.ferreteria.dto;

import com.luno.ferreteria.entity.Category;
import com.luno.ferreteria.entity.SubCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private int id;

    private String name;

    private String description;

    private int price;

    private List<SubCategory> subCategory;

    private String brand;

    private String imageUrl;

    private int stock;

}
