package cr.go.ice.sismo_platform.application.port.in;

import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ReporteQuery {
    Page<ReporteRepositorio> listarReportes(String titulo, String autor, LocalDateTime desde, LocalDateTime hasta, Pageable pageable);
}
