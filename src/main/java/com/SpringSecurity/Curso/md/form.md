## Formulario de inicio de sesión

# Excepciones 
---
- ```AccessDeniedException```: ```"Solicitud no autenticada"```

---

# Flujo de Petición

- ```{username, password}``` --> ```{HttpServletRequest}``` 
- ```{HttpServletRequest}``` --> ```{UsernamePasswordAuthenticationFilter}```
- ```{UsernamePasswordAuthenticationFilter}``` --> ```{UsernamePasswordAuthenticationToken}```
- ```{UsernamePasswordAuthenticationToken}``` --> ```{AuthenticationManager}```
- ```{authenticated ? AuthenticationSuccessHandler : AuthenticationFailureHandler }```