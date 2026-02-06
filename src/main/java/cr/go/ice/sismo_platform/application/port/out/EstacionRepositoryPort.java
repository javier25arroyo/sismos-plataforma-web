package cr.go.ice.sismo_platform.application.port.out;

import cr.go.ice.sismo_platform.domain.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstacionRepositoryPort {
    Page<Estacion> findByCentroCodigo(
            String codigoCentro,
            String codigoEstacion,
            String nombre,
            Double minX,
            Double maxX,
            Double minY,
            Double maxY,
            Pageable pageable
    );
}
