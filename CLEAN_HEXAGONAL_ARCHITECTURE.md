# Arquitectura y Modelo de Dominio - Plataforma de Monitoreo Sísmico

## 🏗️ Arquitectura Implementada

Este proyecto implementa **Clean Architecture** y **Arquitectura Hexagonal (Ports & Adapters)** siguiendo los principios de SOLID para garantizar un sistema mantenible, testeable y escalable.

### 📋 Estructura de Capas

```
src/main/java/cr/go/ice/sismo_platform/
├── domain/                          # 🟢 DOMINIO (Capa más interna)
│   ├── model/                      # Entidades de negocio (Lombok @Data)
│   ├── valueobject/                # Objetos de valor
│   └── exception/                  # Excepciones del dominio
├── application/                     # 🔵 APLICACIÓN (Lógica de Negocio)
│   ├── usecase/                    # Definición e implementación de Casos de Uso
│   │   └── impl/                   
│   ├── service/                    # Servicios de aplicación
│   └── port/                       # Puertos (Interfaces)
│       ├── in/                     # Puertos de entrada (Queries/Commands)
│       └── out/                    # Puertos de salida (Repositorios/External)
├── adapters/                       # 🟡 ADAPTADORES (Infraestructura)
│   ├── in/                         # Adaptadores de entrada (Web/REST)
│   │   └── web/                    
│   │       ├── dto/                # DTOs de transferencia
│   │       └── exception/          # Manejo de excepciones web
│   └── out/                        # Adaptadores de salida (Persistencia)
│       └── persistence/            
│           ├── entity/             # Entidades JPA
│           ├── mapper/             # Mappers Dominio ↔ Persistencia
│           └── repository/         # Repositorios JPA
└── config/                         # ⚙️ CONFIGURACIÓN (Framework)
    ├── SecurityConfig.java
    ├── WebConfig.java
    ├── PersistenceConfig.java
    └── ApplicationConfig.java
```

---

## 🎯 Principios SOLID Aplicados

### **S - Single Responsibility (Responsabilidad Única)**
✅ Cada componente tiene una responsabilidad clara y única:
- **Models**: Representan el estado y reglas del negocio.
- **Use Cases**: Orquestan la lógica de un proceso de negocio específico.
- **Controllers**: Solo manejan la comunicación HTTP y delegan a la aplicación.
- **Mappers**: Transforman datos entre capas sin mezclar lógica.

### **O - Open/Closed (Abierto/Cerrado)**
✅ El sistema permite extensión sin modificar código existente:
- Nuevos casos de uso se agregan como nuevas clases.
- Se pueden añadir nuevos adaptadores (ej. una nueva base de datos o API) implementando los puertos existentes.

### **L - Liskov Substitution (Sustitución de Liskov)**
✅ Las implementaciones son intercambiables:
- Los adaptadores de persistencia implementan interfaces de puerto, permitiendo cambiar la implementación sin afectar la lógica de aplicación.

### **I - Interface Segregation (Segregación de Interfaces)**
✅ Interfaces específicas y granulares:
- Los puertos están divididos por funcionalidad (`UmbralQuery`, `CentroProduccionRepositoryPort`), evitando que las clases dependan de métodos que no usan.

### **D - Dependency Inversion (Inversión de Dependencias)**
✅ Las dependencias apuntan hacia las abstracciones:
- La capa de aplicación no depende de la persistencia o la web; ambas dependen de las interfaces (puertos) definidas en la capa de aplicación/dominio.
- `Adaptadores → Puertos ← Aplicación → Dominio`

---

## 🔌 Arquitectura Hexagonal (Ports & Adapters)

### **Puertos de Entrada (In Ports)**
Definen qué puede hacer el sistema. Los controladores web llaman a estas interfaces.
```java
public interface CentroProduccionQuery {
    Page<CentroProduccion> listarCentros(String codigo, String nombre, Pageable pageable);
}
```

### **Puertos de Salida (Out Ports)**
Definen qué necesita el sistema de agentes externos (BD, APIs).
```java
public interface CentroProduccionRepositoryPort {
    Page<CentroProduccion> findAll(String codigo, String nombre, Pageable pageable);
}
```

### **Adaptadores (Adapters)**
Implementan la comunicación con el mundo exterior (Spring MVC para Web, Spring Data JPA para Persistencia).

---

## 💎 Modelo de Dominio

Las entidades de dominio son el núcleo del sistema. Se utilizan anotaciones de **Lombok** (`@Data`, `@Builder`, `@Accessors(fluent = true)`) para mantener el código limpio y facilitar la inmutabilidad y legibilidad.

### Entidades Principales
1.  **Sismo**: Representa un evento sísmico con magnitud, profundidad y ubicación.
2.  **Estacion**: Datos técnicos y ubicación de las estaciones de monitoreo.
3.  **CentroProduccion**: Centros que agrupan estaciones.
4.  **Umbral**: Límites configurables para alertas sísmicas.
5.  **DatoIntensidadSismica**: Lecturas en tiempo real de aceleración y desplazamiento.
6.  **Usuario**: Gestión de personal y notificaciones.

---

## 🔄 Flujo de Datos

1.  **Request**: El cliente envía una petición HTTP.
2.  **Adaptador In**: El `Controller` recibe el DTO, lo valida y llama a un `UseCase` o `Service` a través de un puerto.
3.  **Aplicación**: El `Service` ejecuta la lógica, interactuando con el `Dominio` y llamando a puertos de salida si requiere datos.
4.  **Adaptador Out**: El `PersistenceAdapter` implementa el puerto de salida, usa mappers para convertir entidades JPA a modelos de dominio y viceversa.
5.  **Response**: El resultado viaja de vuelta, el `Controller` lo convierte a un `ResponseDTO` y lo envía al cliente.

---

## ✅ Beneficios de esta Arquitectura

-   **Testabilidad**: Se pueden probar los casos de uso mediante Mocks de los puertos de salida, sin necesidad de base de datos.
-   **Independencia Tecnológica**: El negocio está protegido de cambios en el framework, base de datos o librerías externas.
-   **Mantenibilidad**: La separación clara de responsabilidades reduce el impacto de los cambios y facilita el entendimiento del sistema.
