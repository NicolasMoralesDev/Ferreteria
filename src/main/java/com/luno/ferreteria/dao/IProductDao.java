package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.Brand;
import com.luno.ferreteria.entity.Product;
import com.luno.ferreteria.entity.SubCategory;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductDao extends JpaRepository<Product, Integer> {


    @Query("SELECT p FROM Product p WHERE p.name LIKE %:search% AND p.status = 'on' AND p.stock > 0")
    Page<Product> findProductsBySearchQuery(@Param("search") String search, Pageable pageable);

//    @Query("SELECT p FROM Product p WHERE p.price LIKE %:category% AND p.status = 'on' AND p.stock > 0")
//    Page<Product> findProductsBySubCategory(@Param("category") int category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 'on' AND p.stock > 0")
    Page<Product> findAllPage(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.status = 'on' ")
    Product findByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.subCategory = :subcategory AND p.stock > 0")
    Page<Product> findAllbySubCategory(@Param("subcategory") SubCategory subcategory, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.stock > 0")
    Page<Product> findAllbyBrand(@Param("brand") Brand brand, Pageable pageable);

    @Override
    @NonNull
    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'on' AND p.stock > 0")
    Optional<Product> findById(@Param("id") @NonNull Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.price = (p.price * (:nuevoPrecio/100)) + p.price WHERE p.brand = :brand")
    void updatePriceProductByBrand(@Param("nuevoPrecio") int nuevoPrecio, @Param("brand") String brand);


    @Query("UPDATE Product p SET p.stock = :stock WHERE p.id = :id")
    void setStockById(@Param("id") int id, @Param("stock") int stock);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'off' WHERE p.id = :id")
    void softDeleteProductById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'on' WHERE p.id = :id")
    void setActiveProductById(@Param("id") int id);


}
