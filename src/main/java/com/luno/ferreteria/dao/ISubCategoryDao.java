package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubCategoryDao extends JpaRepository<SubCategory, Long> {
}
