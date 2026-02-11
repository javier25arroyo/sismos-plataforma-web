package cr.go.ice.sismo_platform.adapters.out.persistence;

import cr.go.ice.sismo_platform.adapters.out.persistence.mapper.UmbralMapper;
import cr.go.ice.sismo_platform.adapters.out.persistence.repository.UmbralJpaRepository;
import cr.go.ice.sismo_platform.application.port.out.UmbralRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.Umbral;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UmbralPersistenceAdapter implements UmbralRepositoryPort {

    private final UmbralJpaRepository repository;

    public UmbralPersistenceAdapter(UmbralJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Umbral> findByParametro(Integer codParametro) {
        return repository.findByCodParametro(codParametro).stream().map(UmbralMapper::toDomain).toList();
    }
}
