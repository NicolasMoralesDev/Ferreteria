package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Long> {
}
