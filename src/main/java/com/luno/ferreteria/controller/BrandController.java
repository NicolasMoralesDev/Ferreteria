package com.luno.ferreteria.controller;


import com.luno.ferreteria.entity.Brand;
import com.luno.ferreteria.service.IBrandService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BrandController {

    @Autowired
    private IBrandService brandserv;

    @Operation(summary = "Endpoint de Acceso a rol privado Admin, crea una marca")
    @PostMapping("admin/brand/create")
    public ResponseEntity<?> postBrand(@RequestBody Brand brand) {
        try {

            HashMap<String, String> response = new HashMap<>();
            String status = brandserv.postBrand(brand);

            if (status == "Marca creada con Exitoo!") {

                response.put("msg", "Marca creada con Exitoo!" );
                return new ResponseEntity<>(response, HttpStatus.CREATED);

            } else {

                response.put("error", status );
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Endpoint de Acceso a rol publico, obtiene todas las marcas")
    @GetMapping("public/brand/get")
    public ResponseEntity<?> getAllBrand() {
        try {
            return new ResponseEntity<>(brandserv.getAllBrand(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Endpoint de Acceso a rol publico, obtiene marca por Id")
    @GetMapping("public/brand/getById")
    public ResponseEntity<?> getBrandById(@RequestParam int id) {
        try {
            return new ResponseEntity<>(brandserv.getBrandById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Endpoint de Acceso a rol privado Admin, borra una marca por Id")
    @DeleteMapping("admin/brand/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int id) {
        try {

            HashMap<String, String> response = new HashMap<>();

            String status = brandserv.deleteById(id);

            if (status == "Marca eliminada con Exito!") {

                response.put("msg", status );

                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("error", status );

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Endpoint de Acceso a rol privado Admin, modifica una marca por Id")
    @PutMapping("admin/brand/put")
    public ResponseEntity<?> putBrandById(@RequestBody Brand brand) {
        try {

            HashMap<String, String> response = new HashMap<>();
            response.put("msg", brandserv.putBrand(brand));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
