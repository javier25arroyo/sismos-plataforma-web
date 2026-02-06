package cr.go.ice.sismo_platform.adapters.out.persistence;

import cr.go.ice.sismo_platform.adapters.out.persistence.mapper.SismoResumenMapper;
import cr.go.ice.sismo_platform.adapters.out.persistence.repository.SismoResumenJpaRepository;
import cr.go.ice.sismo_platform.application.port.out.SismoResumenRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SismoResumenPersistenceAdapter implements SismoResumenRepositoryPort {

    private final SismoResumenJpaRepository repository;

    public SismoResumenPersistenceAdapter(SismoResumenJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<SismoResumen> findResumenUltimoSismo(
            String codCentroPrd,
            String codEstacion,
            LocalDateTime desde,
            LocalDateTime hasta,
            Double magnitudMin,
            Double magnitudMax,
            Pageable pageable
    ) {
        return repository.findResumenUltimoSismo(codCentroPrd, codEstacion, desde, hasta, magnitudMin, magnitudMax, pageable)
                .map(SismoResumenMapper::toDomain);
    }
}
