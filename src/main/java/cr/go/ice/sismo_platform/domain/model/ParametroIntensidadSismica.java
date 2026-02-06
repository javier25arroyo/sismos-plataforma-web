package cr.go.ice.sismo_platform.domain.model;

public record ParametroIntensidadSismica(
        String codParametro,
        String nombre,
        String unidadMedida,
        String simboloUnidad
) {}
