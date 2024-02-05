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
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private ICategoryService cateServ;

    @Operation(summary = "Enpoint de acesso privado para administradores, crea una categoria")
    @PostMapping("/admin/create/category")
    public ResponseEntity<?> createCategory(@RequestBody Category nueva){

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
    public ResponseEntity<?> getAllCategory(){

        try {

            return new ResponseEntity<>(cateServ.getAllcategories(), HttpStatus.ACCEPTED);

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Operation(summary = "Enpoint de acesso public, trae  la categoria por id")
    @GetMapping("/public/get/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){

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
            String status = cateServ.createSubCategory(nueva);

            if (status == "Sub Categoria creada con Exito!") {

                response.put("msg", "Sub Categoria creada con Exito!" );
                return new ResponseEntity<>(response, HttpStatus.CREATED);

            } else {

                response.put("error", status );
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }


        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
