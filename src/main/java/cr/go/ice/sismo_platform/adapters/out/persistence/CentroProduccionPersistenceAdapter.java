package cr.go.ice.sismo_platform.adapters.out.persistence;

import cr.go.ice.sismo_platform.adapters.out.persistence.mapper.CentroProduccionMapper;
import cr.go.ice.sismo_platform.adapters.out.persistence.repository.CentroProduccionJpaRepository;
import cr.go.ice.sismo_platform.application.port.out.CentroProduccionRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CentroProduccionPersistenceAdapter implements CentroProduccionRepositoryPort {

    private final CentroProduccionJpaRepository repository;

    public CentroProduccionPersistenceAdapter(CentroProduccionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<CentroProduccion> findAll(String nombre, Pageable pageable) {
        if (nombre == null || nombre.isBlank()) {
            return repository.findAll(pageable).map(CentroProduccionMapper::toDomain);
        }
        return repository.findByNomCentroPrdContainingIgnoreCase(nombre, pageable)
                .map(CentroProduccionMapper::toDomain);
    }
}
