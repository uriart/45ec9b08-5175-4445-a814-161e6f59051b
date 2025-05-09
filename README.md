Servicio REST desarrollado en Spring Boot para la gestión de usuarios y eslogans.

##  Requisitos

- Java 21
- Maven 3.6+
- Postman (para pruebas de aceptación)

## Compilación y ejecución manual

### Compilar el proyecto

```bash
mvn clean package
```
### Ejecutar la aplicación

```bash
java -jar target/*.jar
```

## Uso con Docker

### Construir la imagen
```bash
docker build . -t mango-test:1.0.0
```

### Ejecutar el contenedor
```bash
docker run -p 8080:8080 mango-test:1.0.0
```

# Ejecutar tests

## Test unitarios e integración
`mvn test`

## Test de aceptación
 - Es necesario tener la aplicación corriendo en localhost:8080 y la base de datos limpia antes de ejecutar las pruebas de aceptación.
 - Importar el archivo `src/test/acceptance/acceptance-tests.postman_collection.json`
 - Ejecutar las pruebas de aceptación con Postman

 
