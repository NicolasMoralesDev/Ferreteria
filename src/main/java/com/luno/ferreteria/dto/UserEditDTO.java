package com.luno.ferreteria.dto;

import com.luno.ferreteria.role.Role;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class UserEditDTO {

    private int userId;

    private String firstName;

    private String lastName;

    private String email;

    @Nullable
    private Double costo;

    private String urlImg;

    private Role rol;


}
