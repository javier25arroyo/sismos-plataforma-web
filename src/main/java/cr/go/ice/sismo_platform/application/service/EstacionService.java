package cr.go.ice.sismo_platform.application.service;

import cr.go.ice.sismo_platform.application.port.in.EstacionQuery;
import cr.go.ice.sismo_platform.application.port.out.EstacionRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstacionService implements EstacionQuery {

    private final EstacionRepositoryPort repository;

    public EstacionService(EstacionRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<Estacion> listarPorCentro(String codigoCentro, String nombre, Pageable pageable) {
        return repository.findByCentroCodigo(codigoCentro, nombre, pageable);
    }
}
