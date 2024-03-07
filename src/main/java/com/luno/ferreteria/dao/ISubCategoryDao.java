package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.Brand;
import com.luno.ferreteria.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISubCategoryDao extends JpaRepository<SubCategory, Integer> {

    @Query("SELECT s FROM SubCategory s WHERE s.title = :title")
    SubCategory getByTitle(@Param("title") String title);
}
