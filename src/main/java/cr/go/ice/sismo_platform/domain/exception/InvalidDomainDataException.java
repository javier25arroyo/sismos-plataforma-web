package cr.go.ice.sismo_platform.domain.exception;

/**
 * Excepción lanzada cuando los datos del dominio son inválidos
 */
public class InvalidDomainDataException extends DomainException {

    public InvalidDomainDataException(String field, Object value) {
        super(String.format("Invalid value '%s' for field '%s'", value, field));
    }

    public InvalidDomainDataException(String message) {
        super(message);
    }
}
