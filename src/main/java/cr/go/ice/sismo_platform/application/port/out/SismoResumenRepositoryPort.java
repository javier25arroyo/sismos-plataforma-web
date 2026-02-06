package cr.go.ice.sismo_platform.application.port.out;

import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface SismoResumenRepositoryPort {
    Page<SismoResumen> findResumenUltimoSismo(
            String codCentroPrd,
            String codEstacion,
            LocalDateTime desde,
            LocalDateTime hasta,
            Double magnitudMin,
            Double magnitudMax,
            Pageable pageable
    );
}
