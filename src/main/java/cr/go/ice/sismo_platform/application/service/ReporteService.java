package cr.go.ice.sismo_platform.application.service;

import cr.go.ice.sismo_platform.application.port.in.ReporteQuery;
import cr.go.ice.sismo_platform.application.port.out.ReporteRepositorioPort;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReporteService implements ReporteQuery {

    private final ReporteRepositorioPort repository;

    public ReporteService(ReporteRepositorioPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<ReporteRepositorio> listarReportes(String titulo, String autor, LocalDateTime desde, LocalDateTime hasta, Pageable pageable) {
        return repository.findAll(titulo, autor, desde, hasta, pageable);
    }
}
