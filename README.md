# sismo-platform (Backend)

Proyecto backend en Java usando Spring Boot destinado a la plataforma "sismo-platform". Este repositorio contiene la lógica del dominio, adaptadores y configuración para ejecutar y probar la API del servicio.

## Visión general

- Arquitectura: arquitectura hexagonal (clean/ports-and-adapters) y principios SOLID aplicados en el modelo de dominio.
- Propósito: servir como backend para la gestión/detección/reportes relacionados con sismos (modelo de dominio y casos de uso implementados).

Documentación adicional incluida:
- CLEAN_HEXAGONAL_ARCHITECTURE.md — explicación de la arquitectura y convenciones del proyecto.
- MODELO_DOMINIO_SOLID.md — notas sobre el diseño del dominio aplicando principios SOLID.
- HELP.md — ayudas y atajos específicos del repositorio.

## Tecnologías

- Java
- Spring Boot
- Gradle (wrapper incluido)

## Estructura principal

- src/ — código fuente del proyecto (dominio, aplicación, infraestructura, configuración).
- build.gradle, settings.gradle — configuración de build con Gradle.
- gradlew, gradlew.bat — wrappers para ejecutar Gradle en Unix/Windows.

## Ejecutar el proyecto (Windows)

1. Compilar: 
   .\gradlew.bat build
2. Ejecutar la aplicación: 
   .\gradlew.bat bootRun
3. Ejecutar tests:
   .\gradlew.bat test

En sistemas Unix/macOS usar `./gradlew` en lugar de `.\gradlew.bat`.


## Licencia y contacto

Agregar aquí la licencia del proyecto y datos de contacto del equipo (o mantener por defecto según política del repositorio).
