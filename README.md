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

## Ejecutar con Docker (Guía Detallada)

Esta es la forma recomendada para producción y desarrollo, ya que garantiza que el entorno sea idéntico en cualquier máquina.

### 1. Requisitos Previos
- **Docker Desktop** instalado y configurado. En Windows Server, asegúrate de tener instalado el motor de Docker (Docker Engine).
- **WSL 2** (Recomendado para Windows) para un mejor rendimiento.
- Al menos 4GB de RAM disponibles para los contenedores (especialmente para SQL Server).

### 2. Configuración del Entorno (.env)
Docker Compose utilizará un archivo `.env` para cargar las credenciales. Crea un archivo llamado `.env` en la raíz del proyecto:

```properties
# Contraseña para el usuario 'sa' de SQL Server (Requerido)
MSSQL_SA_PASSWORD=PasswordSeguro123!

# Configuración de la base de datos para la App
DB_NAME=SismoDB
DB_USER=sa
DB_PASSWORD=PasswordSeguro123!
DB_TRUST_CERT=true

# Puerto en el que responderá la API en el host
SERVER_PORT=8080
```

### 3. Comandos Principales

- **Levantar todo el entorno (Backend + DB):**
  ```powershell
  docker-compose up -d --build
  ```
  *La bandera `--build` asegura que se reconstruya la imagen del backend con los últimos cambios del código.*

- **Verificar que los contenedores están corriendo:**
  ```powershell
  docker ps
  ```

- **Ver logs en tiempo real:**
  ```powershell
  # Para ver ambos servicios
  docker-compose logs -f
  # Solo para la aplicación
  docker-compose logs -f app
  ```

### 4. Persistencia de Datos
El archivo `docker-compose.yml` está configurado con un volumen llamado `mssql_data`. Esto significa que aunque detengas o borres los contenedores, **tus datos no se perderán** a menos que borres explícitamente el volumen de Docker.

### 5. Solución de Problemas Comunes
- **Puerto ocupado:** Si el puerto 8080 o 51433 ya están en uso, cámbialos en el archivo `.env` o en el `docker-compose.yml`.
- **SQL Server no arranca:** Revisa que tu contraseña cumpla con los requisitos de complejidad de Microsoft (letras, números y símbolos).
- **Error de conexión:** La aplicación espera a que la base de datos esté "Healthy" antes de iniciar, gracias a la sección `healthcheck` en el compose.

## Seguridad

Para mantener la integridad y confidencialidad del sistema, sigue estas recomendaciones:

1. **Gestión de Secretos:** Nunca subas el archivo `.env`, `application-secrets.yaml` o cualquier llave privada al repositorio de Git. El archivo `.gitignore` ya está configurado para proteger estos archivos.
2. **Usuario No-Root:** El `Dockerfile` está configurado para ejecutar la aplicación con un usuario de sistema limitado (`spring`), lo que reduce la superficie de ataque en caso de una vulnerabilidad.
3. **Cifrado en Tránsito:** En producción, se recomienda colocar un proxy inverso (como Nginx o IIS) frente al contenedor para manejar la terminación SSL/TLS (HTTPS).
4. **Base de Datos:** Cambia la contraseña por defecto de `sa` inmediatamente y considera crear un usuario con permisos limitados solo para la base de datos `SismoDB`.
5. **Firewall:** Solo permite tráfico externo al puerto de la API (8080). El puerto de la base de datos (51433) debe estar cerrado al tráfico externo o protegido por VPN.

## Despliegue en Windows Server

1. Compilar: 
   .\gradlew.bat build
2. Ejecutar la aplicación: 
   .\gradlew.bat bootRun
3. Ejecutar tests:
   .\gradlew.bat test

En sistemas Unix/macOS usar `./gradlew` en lugar de `.\gradlew.bat`.

## Despliegue en Windows Server

Para entornos de producción en Windows Server, se recomiendan las siguientes opciones:

### Opción A: Docker (Recomendado)
Sigue los mismos pasos de la sección **Ejecutar con Docker**, asegurándote de que el rol de **Containers** esté activo y **Docker Desktop** (o Docker EE) esté configurado para usar contenedores Linux (vía WSL2).

### Opción B: Ejecución como Servicio de Windows (Nativo)

1. **Prerrequisitos:**
   - Instalar [OpenJDK 21](https://adoptium.net/temurin/releases/?version=21).
   - Configurar la variable de entorno `JAVA_HOME`.
   - Descargar [NSSM (Non-Sucking Service Manager)](https://nssm.cc/).

2. **Preparar el binario:**
   Genera el archivo JAR ejecutable:
   ```powershell
   .\gradlew.bat bootJar
   ```
   El archivo se encontrará en `build/libs/sismo-platform-0.0.1-SNAPSHOT.jar`.

3. **Configurar Variables de Entorno:**
   Puedes configurarlas a nivel de sistema o en un script `.ps1` antes de arrancar:
   - `DB_URL`: `jdbc:sqlserver://IP_SERVIDOR:1433;databaseName=SismoDB;encrypt=true;trustServerCertificate=true`
   - `DB_USER`: Usuario de la base de datos.
   - `DB_PASS`: Contraseña de la base de datos.
   - `SERVER_PORT`: (Opcional) Puerto de escucha, por defecto `8080`.
   - `SPRING_PROFILES_ACTIVE`: `prod`

4. **Instalar el servicio con NSSM:**
   ```powershell
   .\nssm.exe install SismoPlatformBackend
   ```
   En la interfaz de NSSM:
   - **Path:** `C:\Path\To\java.exe`
   - **Startup directory:** Carpeta donde esté el JAR.
   - **Arguments:** `-jar sismo-platform-0.0.1-SNAPSHOT.jar`
   - **Environment:** (Pestaña Environment) Agregar las variables `DB_URL`, `DB_USER`, `DB_PASS`, etc.

### Configuración de Firewall
Asegúrate de abrir el puerto **8080** (o el puerto configurado) en el Firewall de Windows para permitir tráfico entrante.

## Licencia

Este proyecto está bajo la **Licencia Apache 2.0**. Para más detalles, consulta el archivo [LICENSE](LICENSE) en la raíz del repositorio.

## Contacto
-  javier25arojas@gmail.com