package com.luno.ferreteria.service;


import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.dto.ProductPaginationDTO;
import com.luno.ferreteria.entity.SubCategory;

import java.util.List;


public interface ProductService {

     String addProduct(ProductDTO productDto);

     ProductPaginationDTO getAllProducts(int page, int limit);

     ProductPaginationDTO getProductByQuery(String q, int page);


     ProductDTO findById(int id);

     void softDeleteProductById(int id);

     void setStockById(int id,int stock);
     void setActiveProductById(int id);

     void addBulkProducts(List<ProductDTO> products);

    ProductDTO updateProduct(ProductDTO updatedProductDto);


     String updatePriceProductByBrand(int nuevoPrecio, String brand);

     ProductPaginationDTO findBySubCategory(int subcategory, int page);

    ProductPaginationDTO findByBrand(int brand, int page);
}
