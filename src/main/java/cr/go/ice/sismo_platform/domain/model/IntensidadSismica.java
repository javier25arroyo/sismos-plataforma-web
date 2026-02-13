package cr.go.ice.sismo_platform.domain.model;

import java.util.Arrays;

/**
 * Intensidades macrosismicas soportadas por la logica del dominio.
 * Incluye codigo de intensidad y su percepcion asociada.
 */
public enum IntensidadSismica {
    II_III("II-III", "Debil"),
    IV("IV", "Suave"),
    V("V", "Moderada"),
    VI("VI", "Fuerte"),
    VII("VII", "Muy fuerte"),
    VIII("VIII", "Severa"),
    IX("IX", "Violenta");

    private final String codigo;
    private final String percepcion;

    IntensidadSismica(String codigo, String percepcion) {
        this.codigo = codigo;
        this.percepcion = percepcion;
    }

    /**
     * Codigo de intensidad (ej: "II-III", "IV", "V").
     */
    public String codigo() {
        return codigo;
    }

    /**
     * Percepcion cualitativa asociada al codigo de intensidad.
     */
    public String percepcion() {
        return percepcion;
    }

    /**
     * Convierte un valor externo (ej: "II-III", "II_III", "V") al enum.
     */
    public static IntensidadSismica fromValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("intensidad no puede ser nula o vacia");
        }

        String normalizado = value.trim().toUpperCase().replace('_', '-');

        return Arrays.stream(values())
                .filter(intensidad -> intensidad.codigo.equals(normalizado) || intensidad.name().replace('_', '-').equals(normalizado))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("intensidad invalida: " + value));
    }
}
