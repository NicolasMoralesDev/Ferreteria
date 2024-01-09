package com.luno.ferreteria.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for the response of the authentication.
 */
@Data
@Builder
public class AuthenticationResponseDTO {
    private String token;
}
