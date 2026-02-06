package cr.go.ice.sismo_platform.application.port.out;

import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CentroProduccionRepositoryPort {
    Page<CentroProduccion> findAll(String codigo, String nombre, Pageable pageable);
}
