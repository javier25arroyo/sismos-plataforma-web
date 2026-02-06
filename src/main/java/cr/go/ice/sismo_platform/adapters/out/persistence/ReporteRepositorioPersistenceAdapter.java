package cr.go.ice.sismo_platform.adapters.out.persistence;

import cr.go.ice.sismo_platform.adapters.out.persistence.mapper.ReporteRepositorioMapper;
import cr.go.ice.sismo_platform.adapters.out.persistence.repository.RepositorioJpaRepository;
import cr.go.ice.sismo_platform.application.port.out.ReporteRepositorioPort;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReporteRepositorioPersistenceAdapter implements ReporteRepositorioPort {

    private final RepositorioJpaRepository repository;

    public ReporteRepositorioPersistenceAdapter(RepositorioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<ReporteRepositorio> findAll(String titulo, String autor, LocalDateTime desde, LocalDateTime hasta, Pageable pageable) {
        return repository.search(titulo, autor, desde, hasta, pageable)
                .map(ReporteRepositorioMapper::toDomain);
    }
}
