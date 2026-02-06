package cr.go.ice.sismo_platform.domain.model;

public record Estacion(
        String codigoCentro,
        String codigoEstacion,
        String nombre,
        Double coordenadaX,
        Double coordenadaY
) {}
