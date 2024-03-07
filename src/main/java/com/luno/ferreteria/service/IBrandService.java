package com.luno.ferreteria.service;

import com.luno.ferreteria.entity.Brand;

import java.util.List;

public interface IBrandService {


    String postBrand(Brand brand);

    List <Brand> getAllBrand();

    Brand getBrandById(int id);

    String deleteById(int id);

    String putBrand(Brand brand);
}
