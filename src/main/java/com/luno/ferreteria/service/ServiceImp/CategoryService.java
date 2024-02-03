package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.dao.ICategoryDao;
import com.luno.ferreteria.dao.ISubCategoryDao;
import com.luno.ferreteria.entity.Category;
import com.luno.ferreteria.entity.SubCategory;
import com.luno.ferreteria.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao cateDao;

    @Autowired
    private ISubCategoryDao cateSubDao;

    @Override
    public String createCategory(Category categoria) {

        try {

           cateDao.save(categoria);
            return "Categoria Creada con Exito!!";
        } catch (Exception e){

            return "Error " + e;
        }
    }

    @Override
    public List<Category> getAllcategories() {

            return cateDao.findAll();


    }

    @Override
    public Category getCategorieById(Long id) {

         return   cateDao.findById(id).orElse(null);

    }

    @Override
    public List<SubCategory> getAllSubCategories() {


          return  cateSubDao.findAll();
    }

    @Override
    public String createSubCategory(SubCategory nueva) {

        try{

            if (cateSubDao.getByTitle(nueva.getTitle()) == null) {

                cateSubDao.save(nueva);

                return "SubCategoria creada con Exito!!";
            } else {
                return "La sub categoria, ya existe!";
            }


        } catch (Exception e){

            return "Error "+e;
        }
    }
}
