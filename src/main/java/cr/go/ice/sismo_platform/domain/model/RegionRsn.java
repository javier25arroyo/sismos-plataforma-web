package cr.go.ice.sismo_platform.domain.model;

public record RegionRsn(
        Integer id,
        String nombre,
        Double coordenadaY,
        Double coordenadaX,
        Integer rango
) {}

