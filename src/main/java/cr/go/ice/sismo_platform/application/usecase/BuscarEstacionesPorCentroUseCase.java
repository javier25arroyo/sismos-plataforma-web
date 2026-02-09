package cr.go.ice.sismo_platform.application.usecase;

import cr.go.ice.sismo_platform.domain.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso: Buscar estaciones por centro
 * Implementa la lógica de negocio para búsqueda de estaciones
 */
public interface BuscarEstacionesPorCentroUseCase {

    /**
     * Busca estaciones por centro con filtros opcionales
     *
     * @param filtros Criterios de búsqueda
     * @return Página de estaciones encontradas
     */
    Page<Estacion> ejecutar(BuscarEstacionesFiltros filtros);

    /**
     * Filtros para búsqueda de estaciones
     */
    record BuscarEstacionesFiltros(
        String codigoCentro,
        String codigoEstacion,
        String nombre,
        Double coordenadaXMinima,
        Double coordenadaXMaxima,
        Double coordenadaYMinima,
        Double coordenadaYMaxima,
        Pageable paginacion
    ) {}
}
