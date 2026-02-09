package cr.go.ice.sismo_platform.domain.valueobject;

import cr.go.ice.sismo_platform.domain.exception.InvalidDomainDataException;

/**
 * Value Object para representar códigos de identificación
 */
public record CodigoIdentificacion(String valor) {

    public CodigoIdentificacion {
        validar(valor);
    }

    private void validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new InvalidDomainDataException("Código de identificación no puede ser nulo o vacío");
        }
        if (valor.length() < 2 || valor.length() > 10) {
            throw new InvalidDomainDataException("Código de identificación debe tener entre 2 y 10 caracteres");
        }
        if (!valor.matches("^[A-Za-z0-9_-]+$")) {
            throw new InvalidDomainDataException("Código de identificación solo puede contener letras, números, guiones y guiones bajos");
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
