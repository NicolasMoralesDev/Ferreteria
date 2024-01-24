package com.luno.ferreteria.service;


import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.dto.ProductPaginationDTO;

import java.util.List;


public interface ProductService {

     ProductDTO addProduct(ProductDTO productDto);

     ProductPaginationDTO getAllProducts(int page);
    
     ProductPaginationDTO getProductByQuery(String q, int page);

     ProductPaginationDTO getProductsBySubCategory(int category, int page);
     ProductDTO findById(int id);

     ProductPaginationDTO findByCategory(String category, int page);

     void softDeleteProductById(int id);

     void setStockById(int id,int stock);
     void setActiveProductById(int id);

     void addBulkProducts(List<ProductDTO> products);

    ProductDTO updateProduct(ProductDTO updatedProductDto);


     String updatePriceProductByBrand(int nuevoPrecio, String brand);
}
