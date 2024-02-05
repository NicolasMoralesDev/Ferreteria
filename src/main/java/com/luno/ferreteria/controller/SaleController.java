package com.luno.ferreteria.controller;


import com.luno.ferreteria.dto.CreateSaleRequestDTO;
import com.luno.ferreteria.dto.SaleDTO;
import com.luno.ferreteria.dto.SalePaginationDTO;
import com.luno.ferreteria.dto.UserSalesRequestDTO;
import com.luno.ferreteria.entity.Sale;
import com.luno.ferreteria.service.ProductService;
import com.luno.ferreteria.service.SaleService;
import com.luno.ferreteria.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    /**
     * Endpoint for save a new sale in the system. The user must be logged in.
     * @param requestDTO CreateSaleRequest, contains the sale data.
     * @return ResponseEntity<SaleDTO>, contains the sale data or an error.
     */
    @Operation(summary = "Endpoint de acceso Rol Usuario, Guarda una orden ")
    @PostMapping("/user/sale/save")
    public ResponseEntity<?> saveSale(@RequestBody CreateSaleRequestDTO requestDTO) {
        SaleDTO saleDTO = saleService.saveSale(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleDTO);
    }



    /**
     * Endpoint for get all the sales of a user. Authentication required. The endpoint is paginated.
     * The admin also can use this endpoint.
     * @param requestDTO UserSalesRequest, contains the userId.
     * @param page int, contains the page number.
     * @return ResponseEntity<SalePagination>, contains the sales data or an error.
     */
    @Operation(summary = "Endpoint para traer las ventas de un Usuario")
    @PostMapping("/user/sale/all")
    public ResponseEntity<?> getSaleByUser(@RequestBody UserSalesRequestDTO requestDTO, @RequestParam int page) {
        SalePaginationDTO response = saleService.saleByUserId(requestDTO, page);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }


    /**
     * Endpoint for get all the sales. Admin role required. The endpoint is paginated.
     * @param page int, contains the page number.
     * @return ResponseEntity<SalePaginationDTO>, contains the sales data or an error.
     */
    @Operation(summary = "Endpoint de administración para traer todas las ventas")
    @GetMapping("/admin/sale/all")
    public ResponseEntity<?> getAllSales(@RequestParam int page) {
        try {
            SalePaginationDTO paginationDTO = saleService.getAllSales(page);
            return ResponseEntity.ok().body(paginationDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Endpoint para actualizar status venta")
    @PutMapping("/user/sale/change")
    public ResponseEntity<?> putStatusSale(@RequestBody CreateSaleRequestDTO sale) {

        try {

            HashMap<String, String> response = new HashMap<>();

            String status = saleService.putStatusSale(sale);
            if (status == "Estado Actualizado correctamente!") {

                response.put("msg",  "Estado Actualizado correctamente!" );
                return ResponseEntity.ok().body(response);

            } else {
                response.put("error",  status);

                return ResponseEntity.badRequest().body(response);

            }


        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
