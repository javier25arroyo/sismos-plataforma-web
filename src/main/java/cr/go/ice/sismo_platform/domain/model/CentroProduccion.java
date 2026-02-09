package cr.go.ice.sismo_platform.domain.model;

public record CentroProduccion(
        String codCentroPrd,
        String nomCentroPrd,
        Double coordenadaX,
        Double coordenadaY,
        Short grupoDespliegue
) {}
