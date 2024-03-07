package com.luno.ferreteria.controller;


import com.luno.ferreteria.entity.UserPro;
import com.luno.ferreteria.service.IUserPro;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserProController {

    @Autowired
    private IUserPro userServ;


    @Operation(summary = "Endpoint publico, trae todos los usuarios profeccionales existentes")
    @GetMapping("public/get/userPro")
    public ResponseEntity<?> getAllUserPro(){
        try {

            HashMap<String, String> status = new HashMap<>();
            List<UserPro> listUsers = userServ.getAllUserPro();

            if ( listUsers == null) {

                status.put("error", "no hay usuarios Profeccionales");
                return new ResponseEntity<>(status ,HttpStatus.BAD_REQUEST );

            } else {

                return new ResponseEntity<>(listUsers ,HttpStatus.OK );

            }

        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage() ,HttpStatus.BAD_REQUEST );
        }
    }
}
