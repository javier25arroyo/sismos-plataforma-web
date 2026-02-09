package cr.go.ice.sismo_platform.adapters.in.web.dto;

public record EstacionResponse(
        String codCentroPrd,
        String codEstacion,
        String nomEstacion,
        Double coordenadaX,
        Double coordenadaY
) {}
