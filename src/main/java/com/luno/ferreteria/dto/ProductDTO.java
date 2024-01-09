package com.luno.ferreteria.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private int id;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    private String brand;

    private String imageUrl;

    private int stock;

}
