package com.luno.ferreteria.service;


import com.luno.ferreteria.dto.AuthenticationResponseDTO;
import com.luno.ferreteria.dto.LoginRequestDTO;
import com.luno.ferreteria.dto.RecoverDTO;
import com.luno.ferreteria.dto.RegisterRequestDTO;

public interface AuthService {

    /**
     * Method for login a user in the system. This method validate the user credentials and generate a token.
     * Then, the token is returned in a AuthenticationResponse object.
     * @param request LoginRequest, contains the user data.
     * @return AuthenticationResponse, contains the token or an error.
     */
    AuthenticationResponseDTO login(LoginRequestDTO request);

    /**
     * Method for register a new user in the system. This method validate the user data and generate a token.
     * Then, the token is returned in a AuthenticationResponse object.
     * @param request RegisterRequest, contains the user data.
     * @return AuthenticationResponse, contains the token or an error.
     */
    AuthenticationResponseDTO register(RegisterRequestDTO request);
    
    String recover(RecoverDTO request);
}
