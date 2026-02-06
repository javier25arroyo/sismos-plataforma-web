package cr.go.ice.sismo_platform.application.port.out;

import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ReporteRepositorioPort {
    Page<ReporteRepositorio> findAll(String titulo, String autor, LocalDateTime desde, LocalDateTime hasta, Pageable pageable);
}
