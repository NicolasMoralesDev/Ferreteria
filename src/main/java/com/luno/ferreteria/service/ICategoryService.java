package com.luno.ferreteria.service;


import com.luno.ferreteria.entity.Category;
import com.luno.ferreteria.entity.SubCategory;

import java.util.List;

public interface ICategoryService {


    String createCategory(Category categoria);

    List<Category> getAllcategories();

    Category getCategorieById(Long id);

    List<SubCategory> getAllSubCategories();
    String createSubCategory(SubCategory nueva);
}
