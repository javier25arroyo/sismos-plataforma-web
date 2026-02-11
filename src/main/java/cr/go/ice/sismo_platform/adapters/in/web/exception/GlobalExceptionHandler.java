package cr.go.ice.sismo_platform.adapters.in.web.exception;

import cr.go.ice.sismo_platform.domain.exception.DomainException;
import cr.go.ice.sismo_platform.domain.exception.InvalidDomainDataException;
import cr.go.ice.sismo_platform.domain.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Manejador global de excepciones para la capa web.
 * Convierte excepciones del dominio y validación a respuestas HTTP consistentes.
 */
@ControllerAdvice
@Tag(name = "Errores", description = "Respuestas de error estándar para la API")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(
            responseCode = "404",
            description = "Recurso no encontrado",
            content = @Content(
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(
                            name = "RecursoNoEncontrado",
                            summary = "Recurso no encontrado",
                            value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Recurso no encontrado",
                                      "status": 404,
                                      "detail": "No existe el centro de producción con código 'CB'",
                                      "category": "RESOURCE_NOT_FOUND",
                                      "timestamp": "2024-03-18T12:30:00"
                                    }
                                    """
                    )
            )
    )
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Recurso no encontrado");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("category", "RESOURCE_NOT_FOUND");

        return problemDetail;
    }

    @ExceptionHandler(InvalidDomainDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(
            responseCode = "400",
            description = "Datos de dominio inválidos",
            content = @Content(
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(
                            name = "DatosInvalidos",
                            summary = "Datos de dominio inválidos",
                            value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Datos inválidos",
                                      "status": 400,
                                      "detail": "El parámetro 'magnitud' debe ser mayor o igual a 0",
                                      "category": "INVALID_DOMAIN_DATA",
                                      "timestamp": "2024-03-18T12:30:00"
                                    }
                                    """
                    )
            )
    )
    public ProblemDetail handleInvalidDomainData(InvalidDomainDataException ex) {
        log.warn("Datos de dominio inválidos: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Datos inválidos");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("category", "INVALID_DOMAIN_DATA");

        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(
            responseCode = "400",
            description = "Argumento inválido",
            content = @Content(
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(
                            name = "ArgumentoInvalido",
                            summary = "Argumento inválido",
                            value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Argumento inválido",
                                      "status": 400,
                                      "detail": "El parámetro 'page' no puede ser negativo",
                                      "category": "INVALID_ARGUMENT",
                                      "timestamp": "2024-03-18T12:30:00"
                                    }
                                    """
                    )
            )
    )
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Argumento inválido");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("category", "INVALID_ARGUMENT");

        return problemDetail;
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(
            responseCode = "500",
            description = "Error del dominio",
            content = @Content(
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(
                            name = "ErrorDominio",
                            summary = "Error del dominio",
                            value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Error del dominio",
                                      "status": 500,
                                      "detail": "Ha ocurrido un error en la lógica de negocio",
                                      "category": "DOMAIN_ERROR",
                                      "timestamp": "2024-03-18T12:30:00"
                                    }
                                    """
                    )
            )
    )
    public ProblemDetail handleDomainException(DomainException ex) {
        log.error("Error del dominio: {}", ex.getMessage(), ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Error del dominio");
        problemDetail.setDetail("Ha ocurrido un error en la lógica de negocio");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("category", "DOMAIN_ERROR");

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(
                            name = "ErrorInterno",
                            summary = "Error interno del servidor",
                            value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Error interno del servidor",
                                      "status": 500,
                                      "detail": "Ha ocurrido un error inesperado",
                                      "category": "INTERNAL_ERROR",
                                      "timestamp": "2024-03-18T12:30:00"
                                    }
                                    """
                    )
            )
    )
    public ProblemDetail handleGenericException(Exception ex) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Error interno del servidor");
        problemDetail.setDetail("Ha ocurrido un error inesperado");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("category", "INTERNAL_ERROR");

        return problemDetail;
    }
}
