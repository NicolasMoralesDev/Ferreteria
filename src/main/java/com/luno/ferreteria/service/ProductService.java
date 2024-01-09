package com.luno.ferreteria.service;


import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.dto.ProductPaginationDTO;

import java.util.List;


public interface ProductService {

    public ProductDTO addProduct(ProductDTO productDto);

    public ProductPaginationDTO getAllProducts(int page);
    
    public ProductPaginationDTO getProductByQuery(String q, int page);

    public ProductDTO findById(int id);

    public ProductPaginationDTO findByCategory(String category, int page);

    public void softDeleteProductById(int id);

    public void setStockById(int id,int stock);
    public void setActiveProductById(int id);

    public void addBulkProducts(List<ProductDTO> products);

    ProductDTO updateProduct(ProductDTO updatedProductDto);
}
