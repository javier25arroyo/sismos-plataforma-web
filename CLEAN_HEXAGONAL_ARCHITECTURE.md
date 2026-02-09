# Arquitectura Clean y Hexagonal - Plataforma de Monitoreo Sísmico

## 🏗️ Arquitectura Implementada

Este proyecto implementa **Clean Architecture** y **Arquitectura Hexagonal (Ports & Adapters)** siguiendo los principios de Uncle Bob Martin y Alistair Cockburn.

### 📋 Estructura de Capas

```
src/main/java/cr/go/ice/sismo_platform/
├── domain/                          # 🟢 DOMINIO (Capa más interna)
│   ├── model/                      # Entidades de negocio
│   └── exception/                  # Excepciones del dominio
├── application/                     # 🔵 APLICACIÓN (Casos de Uso)
│   ├── usecase/                    # Interfaces de casos de uso
│   │   └── impl/                   # Implementaciones de casos de uso
│   └── port/                       # Puertos (Interfaces)
│       ├── in/                     # Puertos de entrada
│       └── out/                    # Puertos de salida
├── adapters/                       # 🟡 ADAPTADORES (Capa externa)
│   ├── in/                         # Adaptadores de entrada
│   │   └── web/                    # Controladores REST
│   │       ├── dto/                # DTOs de transferencia
│   │       └── exception/          # Manejo de excepciones web
│   └── out/                        # Adaptadores de salida
│       └── persistence/            # Persistencia de datos
│           ├── entity/             # Entidades JPA
│           ├── mapper/             # Mappers dominio ↔ persistencia
│           └── repository/         # Repositorios JPA
└── config/                         # ⚙️ CONFIGURACIÓN
    ├── SecurityConfig.java
    ├── WebConfig.java
    ├── PersistenceConfig.java
    └── ApplicationConfig.java
```

---

## 🎯 Principios Clean Architecture Implementados

### 1. **Dependency Rule (Regla de Dependencias)**
✅ **Las dependencias apuntan hacia adentro**
```
Adaptadores → Aplicación → Dominio
    ↑             ↑          ↑
 External    Use Cases   Entities
```

### 2. **Independencia de Frameworks**
✅ **El dominio no conoce Spring, JPA o web frameworks**
- Entidades puras usando Java Records
- Sin anotaciones de framework en el dominio
- Lógica de negocio independiente

### 3. **Independencia de UI**
✅ **La lógica no depende de REST/Web**
- Casos de uso con interfaces puras
- DTOs separados del dominio
- Controladores como adaptadores

### 4. **Independencia de Base de Datos**
✅ **El dominio no conoce sobre persistencia**
- Puertos definen contratos
- Mappers traducen entre capas
- Repositorios implementan puertos

### 5. **Testabilidad**
✅ **Fácil testing por capas**
- Casos de uso testeable sin BD
- Mocks de puertos
- Validaciones en dominio

---

## 🔌 Arquitectura Hexagonal (Ports & Adapters)

### **Puertos de Entrada (In Ports)**
```java
// Casos de uso como puertos de entrada
public interface BuscarCentrosProduccionUseCase {
    Page<CentroProduccion> ejecutar(BuscarCentrosFiltros filtros);
}
```

### **Adaptadores de Entrada (In Adapters)**
```java
// Controladores REST como adaptadores
@RestController
public class CentroController {
    private final BuscarCentrosProduccionUseCase useCase;
    // Adapta HTTP → Caso de Uso
}
```

### **Puertos de Salida (Out Ports)**
```java
// Interfaces para servicios externos
public interface CentroProduccionRepositoryPort {
    Page<CentroProduccion> findAll(...);
}
```

### **Adaptadores de Salida (Out Adapters)**
```java
// Implementaciones de persistencia
@Component
public class CentroProduccionPersistenceAdapter 
    implements CentroProduccionRepositoryPort {
    // Adapta BD → Puerto
}
```

---

## 🛡️ Características de Seguridad

### **Manejo de Excepciones**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    // Convierte excepciones de dominio a HTTP
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleResourceNotFound(...);
}
```

### **Validaciones de Dominio**
```java
public class InvalidDomainDataException extends DomainException {
    // Excepciones específicas del dominio
}
```

---

## 📊 Casos de Uso Implementados

### 1. **Buscar Centros de Producción**
```java
public class BuscarCentrosProduccionUseCaseImpl {
    // Lógica: validación + consulta + mapeado
    public Page<CentroProduccion> ejecutar(BuscarCentrosFiltros filtros);
}
```

### 2. **Buscar Estaciones por Centro**
```java
public class BuscarEstacionesPorCentroUseCaseImpl {
    // Lógica: validación geográfica + filtros + consulta
    public Page<Estacion> ejecutar(BuscarEstacionesFiltros filtros);
}
```

### 3. **Obtener Umbrales por Parámetro**
```java
public class ObtenerUmbralesPorParametroUseCaseImpl {
    // Lógica: validación de parámetros + consulta
    public List<Umbral> ejecutar(ObtenerUmbralesComando comando);
}
```

---

## 🔄 Flujo de Datos

```
HTTP Request
      ↓
[Controller] → Valida entrada, crea comando
      ↓
[Use Case]   → Ejecuta lógica de negocio
      ↓
[Repository] → Accede a datos (vía puerto)
      ↓
[Mapper]     → Convierte Entity → Domain
      ↓
[Use Case]   → Procesa y retorna
      ↓
[Controller] → Convierte a DTO y responde
      ↓
HTTP Response
```

---

## ⚡ Beneficios Obtenidos

### **1. Mantenibilidad**
- Código organizado por responsabilidades
- Cambios localizados a una capa
- Refactoring seguro

### **2. Testabilidad**
- Testing independiente por capa
- Mocking fácil de dependencias
- Tests unitarios rápidos

### **3. Flexibilidad**
- Fácil cambio de tecnologías
- Adaptadores intercambiables
- Evolución incremental

### **4. Escalabilidad**
- Separación clara de responsabilidades
- Casos de uso reutilizables
- Paralelización de desarrollo

---

## 🎯 Principios SOLID Aplicados

### **S - Single Responsibility**
✅ Cada clase tiene una única responsabilidad
- Use Cases: Un caso de uso específico
- Controllers: Un recurso REST
- Mappers: Una conversión específica

### **O - Open/Closed**
✅ Abierto para extensión, cerrado para modificación
- Nuevos casos de uso sin modificar existentes
- Nuevos adaptadores sin cambiar puertos
- Polimorfismo via interfaces

### **L - Liskov Substitution**
✅ Implementaciones intercambiables
- Cualquier implementación de puerto es válida
- Testing con mocks
- Múltiples adaptadores

### **I - Interface Segregation**
✅ Interfaces específicas y pequeñas
- Puertos focalizados
- Casos de uso granulares
- Sin dependencias innecesarias

### **D - Dependency Inversion**
✅ Dependencias hacia abstracciones
- Use Cases dependen de puertos
- Implementaciones dependen de interfaces
- Inversión de control con Spring

---

## 📈 Métricas de Calidad

### **Acoplamiento**
- ✅ **Bajo**: Capas comunicándose via interfaces
- ✅ **Direccional**: Dependencias hacia adentro

### **Cohesión**
- ✅ **Alta**: Cada módulo con responsabilidad clara
- ✅ **Funcional**: Casos de uso cohesivos

### **Complejidad**
- ✅ **Reducida**: Lógica separada por capas
- ✅ **Localizada**: Cambios en una sola capa

---

## 🚀 Próximos Pasos Sugeridos

### **1. Testing**
```bash
# Agregar tests unitarios por capa
src/test/java/
├── domain/          # Tests de entidades y validaciones
├── application/     # Tests de casos de uso
└── adapters/        # Tests de integración
```

### **2. Observabilidad**
```java
// Métricas, logging, tracing
@Component
public class MetricsUseCase {
    // Decorador para casos de uso
}
```

### **3. Eventos de Dominio**
```java
// Comunicación asíncrona
public class SismoDetectadoEvent {
    // Event-driven architecture
}
```

### **4. CQRS**
```java
// Separación Command/Query
public interface CommandHandler<T> {}
public interface QueryHandler<T,R> {}
```

---

## ✅ Resumen Final

El proyecto **Plataforma de Monitoreo Sísmico** ahora implementa correctamente:

🏗️ **Clean Architecture** - Capas bien definidas y regla de dependencias  
🔌 **Hexagonal Architecture** - Puertos y adaptadores implementados  
🛡️ **SOLID Principles** - Los 5 principios aplicados consistentemente  
⚙️ **Separation of Concerns** - Cada capa con responsabilidad específica  
🧪 **High Testability** - Arquitectura preparada para testing  
🔄 **Loose Coupling** - Componentes débilmente acoplados  
📊 **Business Logic Protection** - Dominio protegido de detalles técnicos  

**¡La arquitectura está lista para soportar el crecimiento y evolución del sistema!**
