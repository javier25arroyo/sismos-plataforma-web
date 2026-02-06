package cr.go.ice.sismo_platform.application.port.in;

import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CentroProduccionQuery {
    Page<CentroProduccion> listarCentros(String codigo, String nombre, Pageable pageable);
}
