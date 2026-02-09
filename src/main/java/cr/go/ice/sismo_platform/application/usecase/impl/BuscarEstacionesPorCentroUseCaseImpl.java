package cr.go.ice.sismo_platform.application.usecase.impl;

import cr.go.ice.sismo_platform.application.port.out.EstacionRepositoryPort;
import cr.go.ice.sismo_platform.application.usecase.BuscarEstacionesPorCentroUseCase;
import cr.go.ice.sismo_platform.domain.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Implementación del caso de uso: Buscar estaciones por centro
 */
@Service
public class BuscarEstacionesPorCentroUseCaseImpl implements BuscarEstacionesPorCentroUseCase {

    private final EstacionRepositoryPort repositoryPort;

    public BuscarEstacionesPorCentroUseCaseImpl(EstacionRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Page<Estacion> ejecutar(BuscarEstacionesFiltros filtros) {
        validarFiltros(filtros);
        validarCoordenadas(filtros);

        return repositoryPort.findByCentroCodigo(
            filtros.codigoCentro(),
            filtros.codigoEstacion(),
            filtros.nombre(),
            filtros.coordenadaXMinima(),
            filtros.coordenadaXMaxima(),
            filtros.coordenadaYMinima(),
            filtros.coordenadaYMaxima(),
            filtros.paginacion()
        );
    }

    private void validarFiltros(BuscarEstacionesFiltros filtros) {
        if (filtros == null) {
            throw new IllegalArgumentException("Los filtros no pueden ser nulos");
        }
        if (filtros.paginacion() == null) {
            throw new IllegalArgumentException("La paginación no puede ser nula");
        }
        if (filtros.codigoCentro() == null || filtros.codigoCentro().trim().isEmpty()) {
            throw new IllegalArgumentException("El código del centro es obligatorio");
        }
    }

    private void validarCoordenadas(BuscarEstacionesFiltros filtros) {
        if (filtros.coordenadaXMinima() != null && filtros.coordenadaXMaxima() != null) {
            if (filtros.coordenadaXMinima() > filtros.coordenadaXMaxima()) {
                throw new IllegalArgumentException("La coordenada X mínima no puede ser mayor que la máxima");
            }
        }
        if (filtros.coordenadaYMinima() != null && filtros.coordenadaYMaxima() != null) {
            if (filtros.coordenadaYMinima() > filtros.coordenadaYMaxima()) {
                throw new IllegalArgumentException("La coordenada Y mínima no puede ser mayor que la máxima");
            }
        }
    }
}
