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
                entity.getCoordenadaY(),
                null, // nuSerie no disponible en entity
                null, // idCanal no disponible en entity
                null, // tipoInstrumento no disponible en entity
                null, // eleEstacion no disponible en entity
                null, // tipoSuelo no disponible en entity
                null, // condiSitio no disponible en entity
                null, // deltaT no disponible en entity
                null, // azimut no disponible en entity
                null, // chn1Long no disponible en entity
                null, // chn2Vert no disponible en entity
                null, // chn3Trans no disponible en entity
                null, // bitWeightCh1 no disponible en entity
                null, // bitWeightCh2 no disponible en entity
                null, // bitWeightCh3 no disponible en entity
                null, // vpuCh1 no disponible en entity
                null, // vpuCh2 no disponible en entity
                null, // vpuCh3 no disponible en entity
                null  // modInst no disponible en entity
        );
    }
}
