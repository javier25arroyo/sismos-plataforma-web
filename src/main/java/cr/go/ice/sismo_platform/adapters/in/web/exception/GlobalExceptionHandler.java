package cr.go.ice.sismo_platform.adapters.in.web.exception;

import cr.go.ice.sismo_platform.domain.exception.DomainException;
import cr.go.ice.sismo_platform.domain.exception.InvalidDomainDataException;
import cr.go.ice.sismo_platform.domain.exception.ResourceNotFoundException;
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
 * Manejador global de excepciones para la capa web
 * Convierte excepciones del dominio a respuestas HTTP apropiadas
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
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
