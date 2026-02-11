# sismo-platform (Backend)

Proyecto backend en Java usando Spring Boot destinado a la plataforma "sismo-platform". Este repositorio contiene la lógica del dominio, adaptadores y configuración para ejecutar y probar la API del servicio.

## Visión general

- Arquitectura: arquitectura hexagonal (clean/ports-and-adapters) y principios SOLID aplicados en el modelo de dominio.
- Propósito: servir como backend para la gestión/detección/reportes relacionados con sismos (modelo de dominio y casos de uso implementados).

Documentación adicional incluida:
- CLEAN_HEXAGONAL_ARCHITECTURE.md — explicación de la arquitectura y convenciones del proyecto.
- MODELO_DOMINIO_SOLID.md — notas sobre el diseño del dominio aplicando principios SOLID.

## Tecnologías

- Java 21
- Spring Boot 3.5.x
- Gradle (wrapper incluido)
- Docker & Docker Compose
- SQL Server 2022

## Estructura principal

- src/ — código fuente del proyecto (dominio, aplicación, infraestructura, configuración).
- build.gradle, settings.gradle — configuración de build con Gradle.
- gradlew, gradlew.bat — wrappers para ejecutar Gradle en Unix/Windows.
- Dockerfile — configuración para la contenedorización de la aplicación.
- docker-compose.yml — orquestación de la aplicación y base de datos SQL Server.

## Ejecutar con Docker (Recomendado)

Esta es la forma más rápida de ejecutar el proyecto, ya que configura automáticamente la base de datos SQL Server.

1. **Requisitos:** Tener instalado Docker Desktop y que esté en ejecución.
2. **Configurar variables (local):** crear un archivo `.env` (no se versiona) con:
   ```properties
   MSSQL_SA_PASSWORD=REEMPLAZAR_POR_PASSWORD_SEGURO
   DB_PASSWORD=REEMPLAZAR_POR_PASSWORD_SEGURO
   DB_USER=sa
   DB_NAME=master
   DB_TRUST_CERT=true
   ```
   > `DB_USER`, `DB_NAME` y `DB_TRUST_CERT` son opcionales. Si usas un usuario distinto a `sa`, debes crearlo en la BD.
3. **Levantar el entorno:**
   ```powershell
   docker-compose up -d
   ```
4. **Ver logs (opcional):**
   ```powershell
   docker-compose logs -f app
   ```
5. **Detener el entorno:**
   ```powershell
   docker-compose down
   ```

El backend estará disponible en `http://localhost:8080` y la base de datos en `localhost:51433` (solo localhost).

## Ejecutar el proyecto localmente

1. Compilar: 
   .\gradlew.bat build
2. Ejecutar la aplicación: 
   .\gradlew.bat bootRun
3. Ejecutar tests:
   .\gradlew.bat test

En sistemas Unix/macOS usar `./gradlew` en lugar de `.\gradlew.bat`.


## Licencia y contacto

Agregar aquí la licencia del proyecto y datos de contacto del equipo (o mantener por defecto según política del repositorio).
