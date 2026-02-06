package cr.go.ice.sismo_platform.adapters.out.persistence.mapper;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.CentroProduccionEntity;
import cr.go.ice.sismo_platform.domain.model.CentroProduccion;

public final class CentroProduccionMapper {

    private CentroProduccionMapper() {}

    public static CentroProduccion toDomain(CentroProduccionEntity entity) {
        return new CentroProduccion(entity.getCodCentroPrd(), entity.getNomCentroPrd());
    }
}
