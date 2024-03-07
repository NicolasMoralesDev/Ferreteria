package com.luno.ferreteria.dto;

import com.luno.ferreteria.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    private Role   role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
