package cr.go.ice.sismo_platform.domain.model;

public record ParametroIntensidadSismica(
        Integer codParametro,
        String nomParametro,
        String unidadMedida,
        String simboloUnidad
) {}
