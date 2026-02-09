package cr.go.ice.sismo_platform.adapters.out.persistence.mapper;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.UmbralEntity;
import cr.go.ice.sismo_platform.domain.model.Umbral;

public final class UmbralMapper {

    private UmbralMapper() {}

    public static Umbral toDomain(UmbralEntity entity) {
        return new Umbral(
                Integer.parseInt(entity.getCodParametro()),
                entity.getCodUmbral(),
                entity.getValor(),
                entity.getMensaje(),
                entity.getColor()
        );
    }
}
