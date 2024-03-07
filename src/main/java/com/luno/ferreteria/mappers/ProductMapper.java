package com.luno.ferreteria.mappers;


import com.luno.ferreteria.dao.IBrandDao;
import com.luno.ferreteria.dao.ISubCategoryDao;
import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper {

    @Autowired
    private IBrandDao brandDao;

    @Autowired
    private ISubCategoryDao subCateDao;

    public Product productDtoToProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());
        product.setMedida(productDto.getMedida());
        product.setPrice(productDto.getPrice());
        product.setSubCategory(subCateDao.getByTitle(productDto.getSubCategory()));
        product.setBrand(brandDao.getByTitle(productDto.getBrand()));
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }

    public ProductDTO productToProductDto(Product product) {

        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getIdProduct());
        productDto.setName(product.getName());
        productDto.setStock(product.getStock());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setSubCategory( product.getSubCategory().getTitle());
        productDto.setBrand(product.getBrand().getTitle());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setMedida(product.getMedida());
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