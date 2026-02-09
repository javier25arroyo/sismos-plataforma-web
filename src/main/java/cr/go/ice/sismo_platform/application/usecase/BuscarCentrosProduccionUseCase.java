package cr.go.ice.sismo_platform.application.usecase;

import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso: Buscar centros de producción
 * Implementa la lógica de negocio para búsqueda de centros
 */
public interface BuscarCentrosProduccionUseCase {

    /**
     * Busca centros de producción con filtros opcionales
     *
     * @param filtros Criterios de búsqueda
     * @return Página de centros encontrados
     */
    Page<CentroProduccion> ejecutar(BuscarCentrosFiltros filtros);

    /**
     * Filtros para búsqueda de centros
     */
    record BuscarCentrosFiltros(
        String codigo,
        String nombre,
        Pageable paginacion
    ) {}
}
