package cr.go.ice.sismo_platform.domain.exception;

/**
 * Excepción lanzada cuando no se encuentra un recurso
 */
public class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String resource, String identifier) {
        super(String.format("%s with identifier '%s' not found", resource, identifier));
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

