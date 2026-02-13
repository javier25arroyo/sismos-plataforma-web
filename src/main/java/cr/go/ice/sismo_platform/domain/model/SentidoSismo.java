package cr.go.ice.sismo_platform.domain.model;

import java.util.Arrays;

/**
 * Sentido del evento sismico segun el catalogo de la base de datos.
 * Valores soportados: "S" (sentido) y "N" (no sentido).
 */
public enum SentidoSismo {
    SENTIDO("S"),
    NO_SENTIDO("N");

    private final String databaseValue;

    SentidoSismo(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    /**
     * Valor literal persistido en la base de datos (CHAR(1)).
     */
    public String databaseValue() {
        return databaseValue;
    }

    /**
     * Convierte un valor externo (ej: "S", "N", "SENTIDO") al enum.
     */
    public static SentidoSismo fromValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("sentido no puede ser nulo o vacio");
        }

        String normalizado = value.trim().toUpperCase();

        return Arrays.stream(values())
                .filter(sentido -> sentido.name().equals(normalizado) || sentido.databaseValue.equals(normalizado))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("sentido invalido: " + value));
    }
}
