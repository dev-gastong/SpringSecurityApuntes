# 🛡️ SpringSecurityApp

Proyecto de prueba para conocimientos basicos en Spring Security

---


## Info General:

---

## HTTP REQUEST:

  * Petición:

    * Metodo HTTP:
  
      * GET -> Solicitar Informacion.
      * POST -> Enviar informacion nueva.
      * PUT / PATCH -> Actualizar datos existentes.
      * DELETE -> Borrar un recurso.
  
    * URL -> Ruta exacta del recurso que se pide.
  
    * HEADERS -> Metadatos ocultos con informacion sobre la peticion.
    
    * BODY -> Datos reales que se envian al servidor. Solo se usa en metodos POST / PUT (JSON).


## HTTP RESPONSE:

  * Respuesta:

    * Codigo de estado -> Numero de 3 digitos que le dice al cliente que paso con su peticion.

        * 2xx -> Exito -> Todo salio bien -> 200 OK (Mas famoso).
        * 3xx -> Redireccion -> El recurso se movio a otro lado.
        * 4xx -> Error del cliente -> 404 No existe, 401 No autorizado.
        * 5xx -> Error del servidor -> Fallo en el servidor.

---

##SecurityFilterChain: **Cadena de Filtros de Seguridad**

La cadena de filtros de seguridad es un conjunto ordenado de filtros de Java que interceptan cada HTTP Request que entra al servidor antes de que llegue a los controladores. Su trabajo es decidir si la petición tiene permitido pasar o si debe ser rechazada inmediatamente.

Algunos de los filtros son:

* Cargar y guardar quien esta logueado entre diferentes peticiones.
* Interceptar peticiones de Login -> Revisa parametros para ver si hay usuario / contraseña.
* Atrapar errores de seguridad y decidir que responoderle al usuario.
* Revisar si el usuario autenticado tiene los roles necesarios.

---

## AuthenticationManager: Administrador de Autenticacion. -> Gestiona todo lo que tiene que ver con la autenticacion de usuario.

Su único y principal trabajo es responder a una sola pregunta: ¿Son válidas las credenciales (usuario y contraseña/token) que me acaban de enviar?

Y decir **"Sí, este usuario es quien dice ser"** o **"No, las credenciales son falsas"**.


El AuthenticationManager hace uso de uno o varios AuthenticationProvider.

---


## AuthenticationProvider: Proveedor de Autenticacion.

Es el que sabe realmente como hacer el trabajo de verificacion de credenciales.

El Manager por si mismo no sabe como revisar una BD, o validar un token JWT, o conectarse a un servidor de Facebook para un login social.

Solamente recibe la peticion y pregunta a sus AuthenticationProviders cual es el que sabe procesar el tipo especifico de credenciales.

---

## PasswordEncoder: Codificador de Contraseñas

Es el componente encargado de codificar las contraseñas y validar de que sean correctas.
Este se codifica en un algoritmo llamado **BCrypt**

---

## UserDetailService:

Se encarga de conectarse a cualquier BD y extraer los datos necesarios a verificar.

---

## Flujo normal de Autenticacion

* Request con datos (usr, pass) 
* -> Request pasa por cada filtro de SecurityFIlterChain 
* -> Entra en juego AuthenticationManager una vez que pasa los filtros 
* -> El Manager consulta al provider si el usr se encuentra la BD y la pass es correcta 
* -> El provider envia esta info al UserDetailService para que consulte la BD y le devuelva los datos 
* -> UserDetailService le envia el usuario al provider 
* -> El provider verifica si el usuario coincide validando la contraseña con el password encoder 
* -> Si todo sale correcto el provider le dice al manager que el usuario se encuentra en la bd 
* -> El manager devuelve la response al DelegatingFilterProxy 
* -> FIlterProxy registra al usuario el SecurityContextHolder.



