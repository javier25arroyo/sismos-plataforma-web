package cr.go.ice.sismo_platform.domain.exception;

/**
 * Excepción base para errores del dominio de negocio
 */
public abstract class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
