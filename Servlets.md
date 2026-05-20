# ```Servlets```: Spring Security se monta arriba de la arquitectura de Servlets nativa de Java.

En el mundo de ```Java```, el backend no recibe las peticiones HTTP directamente de internet. Al frente hay un programa llamado Contenedor de Servlets (en el caso de java, ```Tomcat```, que viene metido adentro de Spring Boot de forma invisible).

```El Contenedor``` (```Tomcat```): Recibe las conexiones crudas de internet, gestiona los sockets y traduce esos bytes que viajan por la red en objetos Java legibles (```HttpServletRequest```).

```Un Servlet```: Es una clase especial de Java cuya única función es recibir una petición HTTP, procesarla y devolver una respuesta. En todo Spring Boot hay un solo Servlet principal que maneja todo, llamado el ```DispatcherServlet```. Él recibe la petición y busca a qué ```@RestController``` o método derivársela.

---


## ¿Qué es un Filtro (```Filter```)? ¿Dónde y cuándo operan?:

Antes de que una peticion toque el ```DispatcherServlet``` (y por ende los controladores), el contenedor de Servlets (```Tomcat```) la hace pasar por una Cadena de Filtros (```Filter Chain```).

- ```¿Qué son?```:
  
  -  Clases que implementan la interfaz ```Filter``` de Java.
 
- ```¿Dónde operan?```

  -  Están ubicados fuera de la lógica de Spring propiamente dicha; operan a nivel de ```Tomcat```, interceptando la llamada apenas entra.
 
- ```¿Cuándo operan?```: Operan en orden secuencial. Cada filtro tiene la capacidad de:

  -  Mirar la petición (ej: "¿Trae un token?").

  -  Modificarla o agregarle datos.

  -  Frenar la petición ahí mismo y rebotar al usuario (ej: Devolver un 401 o 403 sin dejarlo llegar al controlador).

  -  Si todo está bien, dejarla pasar al siguiente filtro usando una línea sagrada de código: ```filterChain.doFilter(request, response);```.
 
---

## ¿Cómo se mete Spring Security ahí?

Tomcat maneja sus filtros nativos, pero Spring Security vive adentro del contenedor de Spring. Para comunicarse, Spring Security mete un único filtro nativo en Tomcat llamado ```DelegatingFilterProxy```. Este filtro no hace nada de seguridad; su único trabajo es agarrar la petición de Tomcat y "pasársela" a una cadena interna de filtros de Spring llamada ```SecurityFilterChain```.
