# Modelo de Dominio - Plataforma de Monitoreo Sísmico

## Resumen de Implementación

Se han creado y actualizado las siguientes entidades de dominio basadas en la especificación proporcionada:

### Entidades Creadas/Actualizadas

#### 1. **RegionRsn** (Nueva)
- `id`: Integer
- `nombre`: String
- `coordenadaY`: Double
- `coordenadaX`: Double
- `rango`: Integer

#### 2. **GrupoDespliegue** (Nueva)
- `idRegion`: Short
- `nombreRegion`: String

#### 3. **CentroProduccion** (Actualizada)
- `codCentroPrd`: String
- `nomCentroPrd`: String
- `coordenadaX`: Double
- `coordenadaY`: Double
- `grupoDespliegue`: Short

#### 4. **Estacion** (Actualizada - Completa)
- `codCentroPrd`: String
- `codEstacion`: String
- `nomEstacion`: String
- `coordenadaX`: Double
- `coordenadaY`: Double
- `nuSerie`: String
- `idCanal`: Short
- `tipoInstrumento`: String
- `eleEstacion`: Double
- `tipoSuelo`: String
- `condiSitio`: String
- `deltaT`: Double
- `azimut`: Double
- `chn1Long`: String
- `chn2Vert`: String
- `chn3Trans`: String
- `bitWeightCh1`: Double
- `bitWeightCh2`: Double
- `bitWeightCh3`: Double
- `vpuCh1`: Double
- `vpuCh2`: Double
- `vpuCh3`: Double
- `modInst`: String

#### 5. **ParametroIntensidadSismica** (Actualizada)
- `codParametro`: Integer (cambiado de String)
- `nomParametro`: String
- `unidadMedida`: String
- `simboloUnidad`: String

#### 6. **Umbral** (Actualizada)
- `codParametro`: Integer (cambiado de String)
- `codUmbral`: String
- `limiteInferior`: Double (renombrado de "valor")
- `mensaje`: String
- `color`: String

#### 7. **AlarmaPorEstacion** (Nueva)
- `codCentroPrd`: String
- `codEstacion`: String
- `codParametro`: Integer
- `codUmbral`: String
- `limiteInferior`: Double

#### 8. **DatoIntensidadSismica** (Actualizada)
- `codCentroPrd`: String (renombrado de "codCentro")
- `codEstacion`: String
- `feDato`: LocalDateTime
- `vaAceleracion`: Double
- `vaDesplazamiento`: Double
- `feLectura`: LocalDateTime

#### 9. **Sismo** (Actualizada)
- `feSismo`: LocalDateTime (renombrado de "fechaHora")
- `magnitud`: Double
- `profundidad`: Double
- `ubicacion`: String
- `coordenadaX`: Double
- `coordenadaY`: Double
- `urlFuente`: String
- `mapa`: byte[]
- `intensidades`: String (nuevo)
- `origen`: String (nuevo)
- `sentido`: Character (nuevo)

#### 10. **EventoSismico** (Nueva)
Similar a Sismo, representa eventos sísmicos históricos

#### 11. **SismoMapa** (Nueva)
- `feSismo`: LocalDateTime
- `magnitud`: Double
- `mapa`: byte[]

#### 12. **Acelerograma** (Nueva)
- `feTerremoto`: LocalDateTime
- `coEstacion`: String
- `canal`: String
- `sitio`: String
- `instrumento`: String
- `timeSeg`: Double
- `aceleracion`: Double

#### 13. **Criterio** (Nueva)
- `numRegla`: Integer
- `magnitudMin`: Double
- `distanciaHipocenMax`: Double

#### 14. **Registro** (Nueva)
- `codRegistro`: Integer
- `tituloRegistro`: String
- `feRegistro`: LocalDateTime
- `nomRegistro`: String
- `elevacionEstacion`: Double
- `tipoSuelo`: String
- `condSitio`: String
- `azimut`: Double
- `deltaT`: Double
- `canal1`: Double
- `canal2`: Double
- `canal3`: Double
- `freqMinFilt`: Double
- `freqMaxFilt`: Double
- `numPuntos`: Integer
- `distEpicentral`: Double
- `distHipocentral`: Double

#### 15. **ReporteRepositorio** (Actualizada)
- `feReporte`: LocalDateTime
- `tituloReporte`: String (renombrado de "titulo")
- `resumen`: String
- `url`: String
- `autor`: String
- `archivo`: byte[]

#### 16. **Usuario** (Nueva)
- `cedula`: String
- `nombreCompl`: String
- `correoE`: String
- `telOficina`: String
- `numCelular`: String
- `estadoActivo`: Boolean
- `usuarioNombre`: String
- `notificarSms`: Boolean
- `ultimoAcceso`: LocalDateTime

---

## Cumplimiento de Principios SOLID

### 1. **S - Single Responsibility Principle (Principio de Responsabilidad Única)**

Cada entidad de dominio tiene una **única responsabilidad**:
- **CentroProduccion**: Representa un centro de producción con su ubicación y grupo de despliegue
- **Estacion**: Contiene toda la información técnica de una estación sísmica
- **Umbral**: Define límites de alerta para parámetros sísmicos
- **Usuario**: Maneja información de usuarios del sistema

Los **mappers** están separados y cada uno solo se encarga de convertir una entidad de persistencia a dominio:
- `CentroProduccionMapper`: Solo mapea CentroProduccion
- `EstacionMapper`: Solo mapea Estacion
- `UmbralMapper`: Solo mapea Umbral

Los **controladores** tienen responsabilidades claras:
- `CentroController`: Endpoints para centros de producción
- `EstacionController`: Endpoints para estaciones
- `UmbralController`: Endpoints para umbrales

### 2. **O - Open/Closed Principle (Principio Abierto/Cerrado)**

El diseño está **abierto para extensión pero cerrado para modificación**:

- Las entidades de dominio usan **Java Records** (inmutables), lo que previene modificaciones accidentales
- Los mappers son clases finales con métodos estáticos, evitando herencia innecesaria
- Se pueden añadir nuevos mappers sin modificar los existentes
- Los DTOs de respuesta son independientes del dominio, permitiendo agregar nuevas representaciones sin cambiar el modelo

### 3. **L - Liskov Substitution Principle (Principio de Sustitución de Liskov)**

- No se usa herencia en las entidades de dominio (son records)
- Las interfaces de puerto (`UmbralQuery`, `CentroProduccionQuery`) definen contratos claros
- Las implementaciones de servicio pueden ser sustituidas por cualquier implementación que respete el contrato

### 4. **I - Interface Segregation Principle (Principio de Segregación de Interfaces)**

Las interfaces están **segregadas por funcionalidad**:

- `UmbralQuery`: Solo operaciones de consulta para umbrales
- `CentroProduccionQuery`: Solo operaciones de consulta para centros
- `EstacionQuery`: Solo operaciones de consulta para estaciones
- Cada puerto de salida (`UmbralRepositoryPort`) define solo los métodos necesarios para su caso de uso específico

No hay interfaces "gordas" que obliguen a implementar métodos no necesarios.

### 5. **D - Dependency Inversion Principle (Principio de Inversión de Dependencias)**

El diseño sigue **arquitectura hexagonal** (puertos y adaptadores):

**Capa de Dominio (domain.model)**:
- Entidades puras sin dependencias externas
- No dependen de frameworks ni de la capa de persistencia

**Capa de Aplicación (application)**:
- Puertos de entrada (`port.in`): Interfaces que definen casos de uso
- Puertos de salida (`port.out`): Interfaces que la infraestructura debe implementar
- Servicios que implementan la lógica de negocio

**Capa de Adaptadores (adapters)**:
- `adapters.in.web`: Controladores REST que dependen de puertos de entrada
- `adapters.out.persistence`: Repositorios que implementan puertos de salida
- `adapters.out.persistence.mapper`: Convierten entidades JPA a dominio

**Flujo de dependencias**:
```
Controller → UmbralQuery (interfaz) ← UmbralService → UmbralRepositoryPort (interfaz) ← RepositoryAdapter
```

Las capas de alto nivel (dominio, aplicación) **NO dependen** de las de bajo nivel (persistencia, web).
Las capas de bajo nivel dependen de las abstracciones (interfaces) definidas en las capas de alto nivel.

---

## Correcciones Realizadas

### Controladores
- Actualizados para usar los nuevos nombres de atributos de las entidades de dominio
- `UmbralController`: Cambiado tipo de `codParametro` de String a Integer

### Mappers
- `CentroProduccionMapper`: Actualizado para mapear todos los campos del dominio
- `EstacionMapper`: Actualizado para mapear todos los 23 campos de la entidad completa
- `UmbralMapper`: Agregada conversión de String a Integer para `codParametro`

### DTOs
- Alineados con los nombres de atributos del dominio
- `UmbralResponse`: Cambiado `codParametro` a Integer y `valor` a `limiteInferior`
- `ReporteResponse`: Cambiado `titulo` a `tituloReporte`
- `EstacionResponse`: Actualizados nombres de atributos

### Servicios e Interfaces
- `UmbralQuery` y `UmbralService`: Actualizados para usar Integer en `codParametro`

---

## Compilación

✅ El proyecto compila exitosamente sin errores.

Comando ejecutado:
```bash
.\gradlew clean build -x test --no-daemon
```

Resultado: **BUILD SUCCESSFUL**

---

## Notas Adicionales

1. **Records de Java**: Se utilizan Java Records para las entidades de dominio, lo que proporciona:
   - Inmutabilidad
   - Código más limpio y conciso
   - Métodos equals(), hashCode() y toString() automáticos

2. **Mappers**: Cuando los campos no están disponibles en la entidad de persistencia, se mapean a `null` para mantener compatibilidad con el modelo de dominio completo.

3. **Separación de Responsabilidades**: El modelo de dominio está completamente desacoplado de la capa de persistencia y presentación.

