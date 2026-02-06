package cr.go.ice.sismo_platform.adapters.out.persistence.mapper;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.EstacionEntity;
import cr.go.ice.sismo_platform.domain.model.Estacion;

public final class EstacionMapper {

    private EstacionMapper() {}

    public static Estacion toDomain(EstacionEntity entity) {
        return new Estacion(
                entity.getCodCentroPrd(),
                entity.getCodEstacion(),
                entity.getNomEstacion(),
                entity.getCoordenadaX(),
                entity.getCoordenadaY()
        );
    }
}
