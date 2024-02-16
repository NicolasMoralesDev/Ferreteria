package com.luno.ferreteria.controller;

import com.luno.ferreteria.dto.ProductDTO;
import com.luno.ferreteria.dto.ProductPaginationDTO;
import com.luno.ferreteria.exeptions.ProductNotFoundException;
import com.luno.ferreteria.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;


    // ----   METODOS PUBLICOS
    @Operation(summary = "Endpoint de acceso Rol publico, Traer producto por Id")
    @GetMapping("/public/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(productService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint de acceso Rol publico, Traer productos por SubCategory")
    @GetMapping(value = "/public/products/subCategory", produces = {"application/json"})
    public ResponseEntity<ProductPaginationDTO> getProductBySubCategory(@RequestParam int subcategory, @RequestParam int page) {
        try {

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.findBySubCategory(subcategory, page));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint de acceso Rol publico, Traer productos por Brand")
    @GetMapping(value = "/public/products/brand", produces = {"application/json"})
    public ResponseEntity<ProductPaginationDTO> getProductByBrand(@RequestParam int brand, @RequestParam int page) {
        try {

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.findByBrand(brand, page));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint publico, Traer Todos los Productos que contengan la query")
    @GetMapping("/public/product")
    public ResponseEntity<?> getProductByQuery(@RequestParam String q, @RequestParam int page) {
        try {

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getProductByQuery(q, page));
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
    }

    @Operation(summary = "Endpoint publico, Traer Todos los Productos")
    @GetMapping("/public/products")
    public ResponseEntity<?> getAllProduct(@RequestParam int page, int limit) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getAllProducts(page, limit));
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
    }

    @Operation(summary = "Endpoint para agregar stock a un producto")
    public ResponseEntity<?> setStockById(@RequestBody int id, @PathVariable("stock") int stock){
        try{
            productService.setStockById(id,stock);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Desactiva un producto que este Activado")
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id) {
        try {
            productService.softDeleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Activa un producto que este desactivado")
    @PutMapping("/admin/ActiveProduct/{id}")
    public ResponseEntity<?> activeProductById(@PathVariable("id") int id) {
        try {
            productService.setActiveProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, guardar un producto en la base de datos")
    @PostMapping("/admin/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDto) {
        try {

            HashMap<String, String> response = new HashMap<>();
            response.put("msg", productService.addProduct(productDto) );

           return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Agrega un bulk de productos a la base de datos")
    @PostMapping("/admin/products/bulk")
    public ResponseEntity<?> addBulkProduct(@RequestBody List<ProductDTO> productsDto) {
        try {
            productService.addBulkProducts(productsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Productos agregados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, actualizar un producto en la base de datos")
    @PutMapping("/admin/products")
    public ResponseEntity<ProductDTO> updateProduct( @RequestBody ProductDTO updatedProductDto) {

        try {

            ProductDTO productDTO = productService.updateProduct(updatedProductDto);
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);

        } catch (ProductNotFoundException e) {
            // Manejar el caso donde no se encuentra el producto
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, actualizar los precios por marca")
    @PutMapping("/admin/product/update/bybrand")
    public ResponseEntity<?> updateProductPriceByBrand( @RequestParam int nuevoPrecio, @RequestParam String brand) {

        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("msg", productService.updatePriceProductByBrand(nuevoPrecio, brand) );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
