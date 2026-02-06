package cr.go.ice.sismo_platform.adapters.out.persistence.mapper;

import cr.go.ice.sismo_platform.adapters.out.persistence.repository.SismoResumenProjection;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;

public final class SismoResumenMapper {

    private SismoResumenMapper() {}

    public static SismoResumen toDomain(SismoResumenProjection projection) {
        return new SismoResumen(
                projection.getCodCentroPrd(),
                projection.getNomCentroPrd(),
                projection.getCodEstacion(),
                projection.getNomEstacion(),
                projection.getFeDato(),
                projection.getVaAceleracion(),
                projection.getVaDesplazamiento(),
                projection.getFeSismo(),
                projection.getMagnitud(),
                projection.getProfundidad(),
                projection.getUbicacion(),
                projection.getCoordenadaX(),
                projection.getCoordenadaY()
        );
    }
}
