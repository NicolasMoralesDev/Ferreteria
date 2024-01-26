package com.luno.ferreteria.mappers;


import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper {

    public Product productDtoToProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());
        product.setMedida(productDto.getMedida());
        product.setPrice(productDto.getPrice());
        product.setSubCategory(productDto.getSubCategory());
        product.setBrand(productDto.getBrand());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }

    public ProductDTO productToProductDto(Product product) {

        System.out.println("productStock from DB = " + product.getStock());
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getIdProduct());
        productDto.setName(product.getName());
        productDto.setStock(product.getStock());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setSubCategory(product.getSubCategory());
        productDto.setBrand(product.getBrand());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setMedida(product.getMedida());
        System.out.println("productDto = " + productDto.getStock());
        return productDto;
    }
    public List<ProductDTO> productListToProductDtoList(List<Product> listProduct){
        List<ProductDTO> listProductDto = new ArrayList<>();
        for(Product p : listProduct){
            listProductDto.add(this.productToProductDto(p));
        }
        return listProductDto;
    }
    public List<Product> productDtoListToProductList(List<ProductDTO> listProductDto){
        List<Product> listProduct = new ArrayList<>();
        for(ProductDTO p : listProductDto){
            listProduct.add(this.productDtoToProduct(p));
        }
        return listProduct;
    }


}