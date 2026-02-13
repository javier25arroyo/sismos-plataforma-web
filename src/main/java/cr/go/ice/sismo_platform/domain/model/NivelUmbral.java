package cr.go.ice.sismo_platform.domain.model;

import java.util.Arrays;

/**
 * Niveles de umbral de alerta con jerarquia fija.
 * Se almacenan en base de datos como: "NIVEL 0", "NIVEL 1", "NIVEL 2", "NIVEL 3".
 */
public enum NivelUmbral {
    NIVEL_0("NIVEL 0", 0),
    NIVEL_1("NIVEL 1", 1),
    NIVEL_2("NIVEL 2", 2),
    NIVEL_3("NIVEL 3", 3);

    private final String databaseValue;
    private final int jerarquia;

    NivelUmbral(String databaseValue, int jerarquia) {
        this.databaseValue = databaseValue;
        this.jerarquia = jerarquia;
    }

    /**
     * Valor literal persistido en la base de datos.
     */
    public String databaseValue() {
        return databaseValue;
    }

    /**
     * Orden jerarquico del umbral.
     */
    public int jerarquia() {
        return jerarquia;
    }

    /**
     * Convierte un valor externo (ej: "NIVEL 1" o "NIVEL_1") al enum.
     */
    public static NivelUmbral fromValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("codUmbral no puede ser nulo o vacio");
        }

        String normalizado = value.trim().toUpperCase().replace(' ', '_');

        return Arrays.stream(values())
                .filter(nivel -> nivel.name().equals(normalizado) || nivel.databaseValue.replace(' ', '_').equals(normalizado))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("codUmbral invalido: " + value));
    }
}
