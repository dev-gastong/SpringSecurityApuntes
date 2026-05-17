package com.SpringSecurity.Curso.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()") // => Manejo de rutas con anotaciones
public class TestAuthController {



    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('CREATE')") // == http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAnyAuthority("CREATE");
    public String helloSecured(){
        return "Hello World Secured";
    }


}
