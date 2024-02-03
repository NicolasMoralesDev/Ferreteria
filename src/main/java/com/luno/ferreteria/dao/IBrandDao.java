package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.Brand;
import com.luno.ferreteria.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandDao extends JpaRepository<Brand, Integer> {

    @Query("SELECT b FROM Brand b WHERE b.title = :title")
    Brand getByTitle(@Param("title") String title);
}
