package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandDao extends JpaRepository<Brand, Integer> {
}
