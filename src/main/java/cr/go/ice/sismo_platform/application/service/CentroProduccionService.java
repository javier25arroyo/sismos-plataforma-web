package cr.go.ice.sismo_platform.application.service;

import cr.go.ice.sismo_platform.application.port.in.CentroProduccionQuery;
import cr.go.ice.sismo_platform.application.port.out.CentroProduccionRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CentroProduccionService implements CentroProduccionQuery {

    private final CentroProduccionRepositoryPort repository;

    public CentroProduccionService(CentroProduccionRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<CentroProduccion> listarCentros(String nombre, Pageable pageable) {
        return repository.findAll(nombre, pageable);
    }
}
