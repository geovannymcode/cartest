# CAR-TEST

Este repositorio contiene el código fuente para el proyecto CAR-TEST, que demuestra la implementación y prueba de servicios REST utilizando Spring Boot, JUnit 5 y Mockito.

## Artículo Relacionado

Para una guía detallada sobre cómo este proyecto fue creado y cómo se implementaron las pruebas, puedes leer el artículo completo aquí: [Testing con Spring Boot: Una Guía Actualizada](https://geovannycode.com/testing-con-spring-boot-una-guia-actualizada/).

Este artículo cubre conceptos esenciales como Test-Driven Development (TDD), uso de Mockito para la creación de mocks y la integración de pruebas unitarias sin cargar el contexto completo de Spring. También se exploran las funcionalidades de JUnit 5 y cómo pueden ser aplicadas para crear pruebas robustas y eficientes.

## Ejecutar las Pruebas

Para ejecutar las pruebas unitarias y de integración en este proyecto, necesitarás tener Maven configurado en tu entorno de desarrollo. Puedes ejecutar todas las pruebas utilizando el siguiente comando en la terminal, desde la raíz del proyecto:

```bash
mvn test
```

Este comando ejecutará todas las pruebas definidas en el proyecto y te mostrará los resultados en la terminal, permitiéndote verificar que todo funcione como se espera.

## Ejecutar el Proyecto

Para ejecutar este proyecto, necesitarás tener instalado Java y Maven. Clona este repositorio y luego ejecuta el siguiente comando en la raíz del proyecto:

```bash
mvn spring-boot:run
```

Esto iniciará la aplicación en localhost:8081, donde podrás interactuar con los servicios REST definidos.
