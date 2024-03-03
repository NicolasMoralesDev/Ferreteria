package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.config.JwtService;
import com.luno.ferreteria.dao.IUserDao;
import com.luno.ferreteria.dao.IUserProDAO;
import com.luno.ferreteria.dto.AuthenticationResponseDTO;
import com.luno.ferreteria.dto.LoginRequestDTO;
import com.luno.ferreteria.dto.RecoverDTO;
import com.luno.ferreteria.dto.RegisterRequestDTO;
import com.luno.ferreteria.entity.User;
import com.luno.ferreteria.entity.UserPro;
import com.luno.ferreteria.exeptions.UserValidationException;
import com.luno.ferreteria.role.Role;
import com.luno.ferreteria.service.AuthService;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IUserDao userDao;
    private final IUserProDAO userPro;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO login(LoginRequestDTO request) {

        // User authentication. If the user is not found or the password is incorrect, an exception is thrown.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Once the user is authenticated, the token is generated. Here we save the user in a variable to use it later.
        var user = userDao.findByEmail(request.getEmail()).orElseThrow(
                () -> new UserValidationException("Wrong email.")
        );

        // Generate token with extra claims
        var jwtToken = generateTokenWithExtraClaims(user);

        // Return the token in a AuthenticationResponse object. This object is converted to JSON and returned to the client.
        // it only contains the token.
        return AuthenticationResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponseDTO register(RegisterRequestDTO request) {

        // Validate user data
        validateUser(request);
        String jwtToken = validateRole(request);

        // Return the token in a AuthenticationResponse object. This object is converted to JSON and returned to the client.
        return AuthenticationResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }

    private String validateRole (RegisterRequestDTO request){


        // Create user
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encode password
                .role(request.getRole())
                .urlImg("https://res.cloudinary.com/dore1xodo/image/upload/v1708147493/user-icon-in-trendy-flat-style-isolated-on-grey-background-user-symbol-for-your-web-site-design-logo-app-ui-illustration-eps10-free-vector-modified_gtoub8.png")
                .build();
         userDao.save(user);

        // Generate token with extra claims
        String token = generateTokenWithExtraClaims(user);

        if (request.getRole() == Role.ROLE_PRO) {

            UserPro nuevo = new UserPro();
            User userpro = userDao.findByEmail(request.getEmail()).orElse(null);
            nuevo.setUser(userpro);
            nuevo.setNombre(userpro.getFirstName()+" "+ userpro.getLastName());
            nuevo.setUrlImg(userpro.getUrlImg());
            nuevo.setEmail(userpro.getEmail());
            userPro.save(nuevo);

        }

        return token;
    }


    /**
     * This method validate the user data. If the data is not valid, an
     * exception is thrown.
     *
     * @param request RegisterRequest, contains the user data.
     */
    private void validateUser(RegisterRequestDTO request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new UserValidationException("Email cannot be empty.");
        }

        if (userDao.existsByEmail(request.getEmail())) {
            throw new UserValidationException("Email already exists: " + request.getEmail() + ".");
        }

        if (request.getPassword().length() < 8) {
            throw new UserValidationException("Password must be at least 8 characters long.");
        }

        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new UserValidationException("First name cannot be empty.");
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new UserValidationException("Last name cannot be empty.");
        }
    }

    /**
     * This method generate a token with extra claims. The extra claims are the
     * user data.
     *
     * @param user User, contains the user data.
     * @return String, the token.
     */
    private String generateTokenWithExtraClaims(User user) {
        // Before generate the token, we need to create a map with the extra claims.
        Map<String, Object> extraClaims = new HashMap<>();

        // The extra claims are the user data. This will be in the payload of the token.
        extraClaims.put("id", user.getId());
        extraClaims.put("firstName", user.getFirstName());
        extraClaims.put("lastName", user.getLastName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("urlImg", user.getUrlImg());


        // Generate token with extra claims
        return jwtService.generateToken(extraClaims, user);
    }

    @Override
    public String recover(RecoverDTO request) {

        Resend resend = new Resend("re_Lbz1n7Zw_9ZSSALTnBq75kUTifLyKjjTH");

        if (userDao.existsByEmail(request.getEmail())) {
            
            var user = userDao.findByEmail(request.getEmail()).orElseThrow();
            var idUser = user.getId();
            var jwtToken = generateTokenWithExtraClaims(user);
            String html = "<a"+ " style= " +"text-decoration: none; "+ " href="+"http://34.118.226.11:80/recoverPasword?q="+jwtToken+ "&id="+idUser + ">cambiar contraseña</a>";
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .from("onboarding@resend.dev")
                    .to(request.getEmail())
                    .subject("Restablecer Contraseña!")
                    .html(html)
                    .build();

            try {
                SendEmailResponse data = resend.emails().send(sendEmailRequest);
                System.out.println(data.getId());
                return data.getId();
            } catch (ResendException e) {
                e.printStackTrace();
            }
        }

        return " ";
    }
}
