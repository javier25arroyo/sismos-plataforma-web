package cr.go.ice.sismo_platform.adapters.out.persistence.mapper;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.RepositorioEntity;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;

public final class ReporteRepositorioMapper {

    private ReporteRepositorioMapper() {}

    public static ReporteRepositorio toDomain(RepositorioEntity entity) {
        return new ReporteRepositorio(
                entity.getFeReporte(),
                entity.getTituloReporte(),
                entity.getResumen(),
                entity.getUrl(),
                entity.getAutor(),
                entity.getArchivo()
        );
    }
}
