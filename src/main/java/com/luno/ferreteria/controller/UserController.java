package com.luno.ferreteria.controller;

import com.luno.ferreteria.dto.ChangePasswordRequestDTO;
import com.luno.ferreteria.dto.UserDTO;
import com.luno.ferreteria.dto.UserEditDTO;
import com.luno.ferreteria.mappers.UserMapper;
import com.luno.ferreteria.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Operation(summary = "Endpoint privado, trae todos los usuarios existentes")    
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        try {
            List<UserDTO> listUserDto = userMapper.listUserToListUserDto(userService.findAll());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(listUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Operation(summary = "Endpoint privado, trae un usuario por su id")
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
        try {
            UserDTO userDto = userMapper.userToUserDto(userService.findById(id));
            return ResponseEntity.status(HttpStatus.FOUND).body(userDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * This endpoint is used to change the password of a user. Its neccesary to send the old password and the new password.
     * @param request ChangePasswordRequest, contains the userId, the old password and the new password with the
     *                confirmation password.
     * @return ResponseEntity, contains the status of the request.
     */
    @Operation (summary = "Endpoint privado, cambia la contraseña de un usuario")
    @PutMapping("/user/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO request){
        try {
            userService.changePassword(request);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Endpoint privado, edita el usuario")
    @PutMapping("/user/edit")
    public  ResponseEntity<?> editUser (@RequestBody UserEditDTO user){

        HashMap<String, String> status = new HashMap<>();
       try {

           String response = userService.editUser(user);

           if (response == "Usuario Editado con Exito!") {

               status.put("msg", "usuario editado con Exito!");
               return  ResponseEntity.ok().body(status);

           } else {
               status.put("error", "se ha produccido un problema!");
               return ResponseEntity.badRequest().body(status);
           }

       } catch (Exception e){

           status.put("error", e.getMessage());
           return ResponseEntity.badRequest().body(status);
        }

    }
}
