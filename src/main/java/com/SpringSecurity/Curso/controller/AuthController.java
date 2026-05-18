package com.SpringSecurity.Curso.controller;


import com.SpringSecurity.Curso.dto.LoginRequest;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Aquí generas tu JWT o devuelves la respuesta de éxito
            return ResponseEntity.ok("Login exitoso");

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            // Es una buena práctica de seguridad devolver el mismo mensaje para ambos
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La cuenta se encuentra deshabilitada");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La cuenta está bloqueada");
        }


    }


}
