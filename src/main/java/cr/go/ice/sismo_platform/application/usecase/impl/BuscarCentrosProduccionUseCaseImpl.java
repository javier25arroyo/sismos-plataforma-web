package cr.go.ice.sismo_platform.application.usecase.impl;

import cr.go.ice.sismo_platform.application.port.out.CentroProduccionRepositoryPort;
import cr.go.ice.sismo_platform.application.usecase.BuscarCentrosProduccionUseCase;
import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Implementación del caso de uso: Buscar centros de producción
 */
@Service
public class BuscarCentrosProduccionUseCaseImpl implements BuscarCentrosProduccionUseCase {

    private final CentroProduccionRepositoryPort repositoryPort;

    public BuscarCentrosProduccionUseCaseImpl(CentroProduccionRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Page<CentroProduccion> ejecutar(BuscarCentrosFiltros filtros) {
        validarFiltros(filtros);

        return repositoryPort.findAll(
            filtros.codigo(),
            filtros.nombre(),
            filtros.paginacion()
        );
    }

    private void validarFiltros(BuscarCentrosFiltros filtros) {
        if (filtros == null) {
            throw new IllegalArgumentException("Los filtros no pueden ser nulos");
        }
        if (filtros.paginacion() == null) {
            throw new IllegalArgumentException("La paginación no puede ser nula");
        }
    }
}
