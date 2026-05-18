package com.SpringSecurity.Curso.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()") // => Manejo de rutas con anotaciones
public class TestAuthController {



    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
        return "Hello World - GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloPost(){
        return "Hello World - POST";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String helloPut(){
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public String helloDelete(){
        return "Hello World - DELETE";
    }


}
