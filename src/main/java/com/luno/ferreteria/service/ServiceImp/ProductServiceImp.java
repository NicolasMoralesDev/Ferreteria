package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.dao.IProductDao;
import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.dto.ProductPaginationDTO;
import com.luno.ferreteria.dto.UpdatedProductDto;
import com.luno.ferreteria.entity.Product;
import com.luno.ferreteria.entity.SubCategory;
import com.luno.ferreteria.mappers.ProductMapper;
import com.luno.ferreteria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    IProductDao productDao;
    @Autowired
    ProductMapper productMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDto) {
        try {
            Product product = productMapper.productDtoToProduct(productDto);


            return productMapper.productToProductDto(productDao.save(product));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ProductPaginationDTO getAllProducts(int page) {

        // objeto pagina
        Pageable pageable = PageRequest.of(page, 10);

        // crea el listado de productos paginable
        ProductPaginationDTO listProducts = new ProductPaginationDTO();

        // se obtienen los datos de la BD
        Page<Product> productsPage = productDao.findAll(pageable);

        // se mapean los productos a ProductDTO
        List<ProductDTO> productDTOs = productMapper.productListToProductDtoList(productsPage.getContent());

        // se setean los datos en el ProductPaginationDTO
        listProducts.setPage(page);
        Page<Product> productList = productDao.findAllPage(pageable);
        List<ProductDTO> productDtoList = productMapper.productListToProductDtoList(productList.getContent());
        listProducts.setProductos(productDtoList);
        listProducts.setTotal(productList.getTotalPages());

        return listProducts;
    }

    @Override
    public ProductPaginationDTO findBySubCategory(int subCategory, int page) {

        SubCategory nueva = new SubCategory();
        nueva.setIdSubCategory(subCategory);

        // objeto pagina
        Pageable pageable = PageRequest.of(page, 10);

        // crea el listado de productos paginable
        ProductPaginationDTO listProducts = new ProductPaginationDTO();

        // se obtienen los datos de la BD
        Page<Product> productsPage = productDao.findAllbySubCategory(nueva, pageable);

        // se mapean los productos a ProductDTO
        List<ProductDTO> productDTOs = productMapper.productListToProductDtoList(productsPage.getContent());

        // se setean los datos en el ProductPaginationDTO
        listProducts.setPage(page);
        Page<Product> productList = productDao.findAllbySubCategory(nueva, pageable);
        List<ProductDTO> productDtoList = productMapper.productListToProductDtoList(productList.getContent());
        listProducts.setProductos(productDtoList);
        listProducts.setTotal(productList.getTotalPages());

        return listProducts;
    }

    @Override
    public void setStockById(int id, int stock) {
        productDao.setStockById(id, stock);
    }

    @Override
    public ProductDTO findById(int id) {
        return productMapper.productToProductDto(productDao.findById(id).get());
    }

    @Override
    public void softDeleteProductById(int id) {
        productDao.softDeleteProductById(id);

    }

    @Override
    public void setActiveProductById(int id) {
        productDao.setActiveProductById(id);
    }

    @Override
    public void addBulkProducts(List<ProductDTO> productsDto) {
        productDao.saveAll(productMapper.productDtoListToProductList(productsDto));
    }

    @Override
    public ProductPaginationDTO getProductByQuery(String q, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        // crea el listado de productos paginable
        ProductPaginationDTO listProducts = new ProductPaginationDTO();

        // se setean los datos devueltos por la bd y se modela un dto
        listProducts.setPage(page);
        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findProductsBySearchQuery(q, pageable).getContent()));
        listProducts.setTotal(productDao.findProductsBySearchQuery(q, pageable).getTotalPages());
        return listProducts;
    }

    @Override
    public ProductPaginationDTO getProductsBySubCategory(int category, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        ProductPaginationDTO listProducts = new ProductPaginationDTO();

        listProducts.setPage(page);
//        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findProductsBySubCategory(category, pageable).getContent()));
//        listProducts.setTotal(productDao.findProductsBySubCategory(category, pageable).getTotalPages());

        return listProducts;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO updatedProductDto) {


        // Guardar el producto actualizado
        Product product = productMapper.productDtoToProduct(updatedProductDto);
        product.setIdProduct(updatedProductDto.getId());
        Product prod = productDao.save(product);
        return productMapper.productToProductDto(prod);

    }

    @Override
    public String updatePriceProductByBrand(int nuevoPrecio, String brand) {

        try {
            productDao.updatePriceProductByBrand(nuevoPrecio, brand);
            return "Precio Actualizado correctamente!";
        } catch (Exception e) {
            return "Error " + e;
        }
    }

    private static Product getProduct(UpdatedProductDto updatedProductDto, Optional<Product> optionalProduct) {
        Product existingProduct = optionalProduct.get();

        // Actualizar los campos del producto existente con los nuevos datos
        existingProduct.setName(updatedProductDto.getName());
        existingProduct.setBrand(updatedProductDto.getBrand());
        existingProduct.setDescription(updatedProductDto.getDescription());
        existingProduct.setSubCategory(updatedProductDto.getSubCategory());
        existingProduct.setPrice(updatedProductDto.getPrice());
        existingProduct.setImageUrl(updatedProductDto.getImageUrl());
        existingProduct.setStock(updatedProductDto.getStock());
        return existingProduct;
    }
}
