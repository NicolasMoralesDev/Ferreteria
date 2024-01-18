package com.luno.ferreteria.controller;

import com.luno.ferreteria.entity.Category;

import com.luno.ferreteria.entity.SubCategory;
import com.luno.ferreteria.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ICategoryService cateServ;

    @Operation(summary = "Enpoint de acesso privado para administradores, crea una categoria")
    @PostMapping("/admin/create/category")
    public ResponseEntity<?> createCategory(@RequestBody Category nueva){
        System.out.println(nueva);
        try {


            HashMap<String, String> response = new HashMap<>();
            response.put("msg", cateServ.createCategory(nueva) );

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Operation(summary = "Enpoint de acesso publico, trae todas las categorias")
    @GetMapping("/public/get/category")
    public ResponseEntity<?> getAllCategories(){

        try {

            return new ResponseEntity<>(cateServ.getAllcategories(), HttpStatus.ACCEPTED);

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Operation(summary = "Enpoint de acesso public, trae  la categoria por id")
    @GetMapping("/public/get/category/{id}")
    public ResponseEntity<?> getCategorieById(@PathVariable Long id){

        try {

            return new ResponseEntity<>(cateServ.getCategorieById(id), HttpStatus.ACCEPTED);

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }


    @Operation(summary = "Enpoint de acesso publico, trae todas las SubCategorias")
    @GetMapping("/public/get/subCategories")
    public ResponseEntity<?> getAllSubCategories(){

        try {

            return new ResponseEntity<>(cateServ.getAllSubCategories(), HttpStatus.ACCEPTED);

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }


    @Operation(summary = "Enpoint de acesso privado para administradores, crea una Sub categoria")
    @PostMapping("/admin/create/subcategory")
    public ResponseEntity<?> createSubCategory(@RequestBody SubCategory nueva){

        try {


            HashMap<String, String> response = new HashMap<>();
            response.put("msg", cateServ.createSubCategory(nueva) );

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
