package cr.go.ice.sismo_platform.adapters.out.persistence;

import cr.go.ice.sismo_platform.adapters.out.persistence.mapper.EstacionMapper;
import cr.go.ice.sismo_platform.adapters.out.persistence.repository.EstacionJpaRepository;
import cr.go.ice.sismo_platform.application.port.out.EstacionRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EstacionPersistenceAdapter implements EstacionRepositoryPort {

    private final EstacionJpaRepository repository;

    public EstacionPersistenceAdapter(EstacionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Estacion> findByCentroCodigo(String codigoCentro, String nombre, Pageable pageable) {
        if (nombre == null || nombre.isBlank()) {
            return repository.findByCodCentroPrd(codigoCentro, pageable).map(EstacionMapper::toDomain);
        }
        return repository.findByCodCentroPrdAndNomEstacionContainingIgnoreCase(codigoCentro, nombre, pageable)
                .map(EstacionMapper::toDomain);
    }
}
