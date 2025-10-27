# PruebaTecnicaBackend-LinkTIC-Products

# Instrucciones de Instalación y Ejecucción
  ##Lenguaje de programación
    - Spring Boot Java
    - Java 21
    - SQL Server
  ##Instalación Spring Boot Java
    Todas las instalaciones se deben realizar mediante la línea de comandos o CMD en Windows      
    ## Java JDK
      ## Verificar instalación:
        - java -version
      ## Sí no esta instalado, proceder a descargar del sitio:
        - https://adoptium.net
    ## Maven
      ## Verficar Maven
        - mvn -version
      ## Sí no esta instalado, proceder a descargar desde del sitio:
        - https://maven.apache.org/download.cgi
        - Descomprime y agrega la ruta de Maven a la variable de entorno PATH.

  ##Instalación IDE
    El IDE para Java es el Entorno de Desarrollo Integrado y se pueda usar uno de los siguientes:
  
    ##  IntelliJ IDEA (recomendado)
        Para descargarlo puedes acceder al siguiente link:
            - https://www.jetbrains.com/idea/download/?section=windows
    ##  Eclipse IDE for Java
        Para descargarlo puedes acceder al siguiente link:
            - https://eclipseide.org/
    ##  VS Code con extensiones Java
        Para descargarlo puedes acceder al siguiente link:
            - https://code.visualstudio.com/download

  ##Instalación Motor de Base de Datos
    La base de datos de la prueba técnica de Back End se creó en SQL Server 2022 y por lo tanto se debe descargar e instalar lo siguiente:

    ##  SQL Server Management Studio (SSMS)
        Para descargarlo puedes acceder al siguiente link:
            - [https://www.jetbrains.com/idea/download/?section=windows](https://learn.microsoft.com/es-es/ssms/install/install)

    ##  SQL Server Configuration Manager
        Para descargarlo puedes acceder al siguiente link:
            - https://learn.microsoft.com/en-us/sql/tools/configuration-manager/sql-server-configuration-manager?view=sql-server-ver17

  ##Instalación Docker Desktop
    Con el fin de realizar pruebas de contenerización del despliegue de los microservicios tanto de Productos como de Inventarios se debe instalar:

    ##  Docker Desktop
        Para descargarlo puedes acceder al siguiente link:
            - https://docs.docker.com/desktop/setup/install/windows-install/

   ##Ejecución Proyecto PruebaTecnicaBackend-LinkTIC-Inventory
     Las pruebas se pueden realizar cargando el proyecto en Eclipse o Ejecutando la Imagen de Docker

     ##Pruebas en Eclipse
       ##  Descargar el proyecto de Github.com
           Para descargarlo puedes acceder al siguiente link:
            - https://github.com/jgaleano2018/PruebaTecnicaBackend-LinkTIC-Inventory
            <img width="2562" height="1005" alt="image" src="https://github.com/user-attachments/assets/a4ff9636-66aa-46ea-b889-7a37a25a57ea" />
       ##  Realizar un Clon del Repositorio:
             <img width="2556" height="1007" alt="image" src="https://github.com/user-attachments/assets/deef782c-c20f-481c-bba4-d0dd747583ef" />
             <img width="2560" height="1038" alt="image" src="https://github.com/user-attachments/assets/72f88607-a10b-491e-a6ca-738134b97f90" />
             <img width="581" height="371" alt="image" src="https://github.com/user-attachments/assets/3018658e-8efc-487d-a0f9-9ac2a20a9578" />
             <img width="581" height="371" alt="image" src="https://github.com/user-attachments/assets/1f4e913a-b522-4f28-870a-0119f98b38b0" />
             <img width="2573" height="1039" alt="image" src="https://github.com/user-attachments/assets/15b46148-cdcc-42e3-bf59-edff8d239466" />
       ##  Ejecutar proyecto en Maven
             <img width="1001" height="1043" alt="image" src="https://github.com/user-attachments/assets/33123909-1863-4529-9163-09e3b0c089a9" />
             <img width="711" height="692" alt="image" src="https://github.com/user-attachments/assets/3d04a1b1-918f-4c0e-a8fc-96621a4d0097" />
             <img width="2557" height="1042" alt="image" src="https://github.com/user-attachments/assets/bcde6b2b-43b0-40ec-b870-6fd350ccd62f" />
       ##  Ejecutar pruebas unitarias (Test)
             <img width="905" height="1038" alt="image" src="https://github.com/user-attachments/assets/c718c1a9-4f2f-43e6-bb94-eef0a4d67999" />
       ##  Generar reporte de cobewrtura de pruebas unitarias
             <img width="2560" height="1043" alt="image" src="https://github.com/user-attachments/assets/fc1a0584-a80a-440c-aa9d-a02e9ffbd16f" />

    ##Pruebas en Docker Desktop
      Se acceder a la aplicación Docker Desktop

      ##Ejecutar el contenedor
           - Ubicar el contenedor de spring-boot en el puerto 8081:8081
              <img width="1365" height="728" alt="image" src="https://github.com/user-attachments/assets/1facd485-4691-49d8-b26d-f6bc0bd274bf" />
      ##Ejecutar la imágen
          - Ubicar la imágen spring-boot-docker:
              <img width="1366" height="730" alt="image" src="https://github.com/user-attachments/assets/20bcfd67-6cfd-4f22-ac3b-ad87ed952aa7" />

    
# Descripción de la Arquitectura
  - La arquitectura usada para la creación del microservicio de Inventarios es Clean Arquitecture o Hexagonal Arquitecture, donde se realizó separación por capas:
    . Capa de Adapater: En esta capa se adicionaron los DTOs, Controllers y Mappers
    . Capa de Domain: En esta capa se adicionaron los Models, Casos de Usos, Interfaces, Configs
    . Capa de Aplicación: En esta capa se adicionaron los Servicios
    . Capa de Persistencia: En esta capa se adicionaron las Entities y Acceso a Base de Datos SQL Server 2022

## Decisiones Técnica y justificaciones:
   - Se utilizó la arquitectura limpia o Hexagaonal Arquitecture con el fin de que se aplique los conceptos de SOLID y la apliación sea escalable, dinamica, segura y fácil de entender en la operación.
   - El endpoint de compras se adición en el microservicio de Productos porque las compras estan relacionadas principalmente con los Productos y desde este microservicio se controla de igual forma el manejo del stock del inventario.
   - Se implemento el patrón de diseño Singleton en el loggin del microservicio con Usuario y Password en la carga del archivo Configuration de SecurityConfig.java
       <img width="2560" height="1041" alt="image" src="https://github.com/user-attachments/assets/c5b8899b-07bd-4720-b27a-5531a86dd540" />
   - Se implemento el patrón de diseño Abstract Factory:
       <img width="2557" height="1035" alt="image" src="https://github.com/user-attachments/assets/cef7ad0a-38b4-4c2b-835f-dd904feb51c5" />
   - Se implemento el patrón de diseño Repository:
       <img width="2557" height="1039" alt="image" src="https://github.com/user-attachments/assets/e0d16335-0a9a-4074-a66f-07c870af10e2" />

# Diagramas de la apliación:
  ##Diagrama de Componentes:
    - <img width="1375" height="1135" alt="image" src="https://github.com/user-attachments/assets/b7111d0f-98ea-4b43-b6e7-05a2398e6ba7" />
  ##Diagrama de Flujos de Datos:
    <img width="1283" height="804" alt="image" src="https://github.com/user-attachments/assets/91467f02-3080-41b8-adeb-7e6305072ad4" />
  ##Diagrama de Clases:
    - <img width="1189" height="809" alt="image" src="https://github.com/user-attachments/assets/f291669c-f874-42e5-af78-a04545a6e838" />

# Flujo de Compras Implementado:
  - El endpoint de compras es de tipo POST y recibé el modelo de la entidad Compras (ProductID, TotalCompra, FechaCreacion, FechaActualizacion)
  - Luego se consume el servicio de PurchaseService y se consulta la disponibilidad en el inventario, para lo cual se consume el servicio de Inventario mediante un WebClient de Webflux que realiza la conexión al microservicio de inentarios por el puerto 8081 y teniendo como base el envío del X-API-KEY en el header de     petición HTTP.
  - Luego si hay disponibilidad en el inventario se deduce la cantidad a comprar, se registra la compra y se actualiza el inventario.
  - En el caso que en el inventario no alla dispoinibilidad de stock se envía el mensaje respectivo.

# Herramientas de IA:
  - Se utilizó Chat GPT y Gemini
  - La calidad del código generado se valido al integrar los bloques de código en la aplicación de Spring Boot Java y Eclipse y finalmente al realizar las pruebas funcionales se detecto el correcto funcianamiento.
  - También se utilizó Postman para probar los endpoints y cuyas colecciones se envian por correo.

