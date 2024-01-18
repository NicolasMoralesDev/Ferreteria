package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubCategoryDao extends JpaRepository<SubCategory, Integer> {
}
