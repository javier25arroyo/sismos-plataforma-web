package cr.go.ice.sismo_platform.adapters.in.web.dto;

public record EstacionResponse(
        String codigoCentro,
        String codigoEstacion,
        String nombre,
        Double coordenadaX,
        Double coordenadaY
) {}
