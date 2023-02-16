A continuación se describe la estructura y funcionamiento de cada una de las soluciones.
###
1. [Spring Boot]
-Se crea proyecto en Spring Boot(carsolution) donde se implementan cada una de las funcionalidades requeridas para ello deben de realizarse las siguientes configuraciones.
->Tener instalado y en ejecución mysql service.
->Editar la propiedad spring.datasource.url del application.properties y colocar la correspondiente al servicio mysql.
->El sistema tiene integrado seguridad mediante JWT, en las properties esta descrito user y pass(application.user,application.pass) para autenticacion endpoint (../api/v1/autheticante), con el JWT obtenido en la cabecera de las peticiones como Bearer TOKEN se tiene acceso a todos los demás endpoints.
->Aclarar que tanto la BD como las tablas se generan automanticamente una vez corre la aplicación.

2. [MySQL]
-Se crea archivo solucion_2.sql que contiene las query generadas.

3. [Spring Boot]
-Se crea proyecto Spring Boot(employeessolution) donde se implementa las consultas de obtención de datos del la pregunta 2 para ello deben de realizarse las siguientes configuraciones.
->Tener instalado y en ejecución mysql service.
->Editar la propiedad spring.datasource.url del application.properties y colocar la correspondiente al servicio mysql.
->Crear schema de base de datos con nombre data_myhotel y ejectar el dump enviado en el ejercicio 2.
