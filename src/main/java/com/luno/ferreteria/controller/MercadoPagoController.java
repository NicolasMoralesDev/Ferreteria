
package com.luno.ferreteria.controller;

import com.luno.ferreteria.dto.MercadoPagoDTO;
import com.luno.ferreteria.service.IMpService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Nico Morales
 */

@RestController
@RequestMapping("/api/user/sales")
@CrossOrigin(origins = "*")
public class MercadoPagoController {
    
    @Autowired
    private IMpService mpserv;
    
    @Operation(summary = "Endpoint de acceso Rol privado, genera pago con Mercado Pago")
    @PostMapping("/pay")
    public ResponseEntity<?> postPayOrder(@RequestBody MercadoPagoDTO pago ){
        try {
            
                   return new ResponseEntity(mpserv.OrderMp(pago), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
}
