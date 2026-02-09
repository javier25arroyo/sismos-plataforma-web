package cr.go.ice.sismo_platform.application.usecase;

import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 * Caso de uso: Obtener resumen de últimos sismos
 * Implementa la lógica de negocio para consulta de resumen sísmico
 */
public interface ObtenerResumenSismosUseCase {

    /**
     * Obtiene resumen de sismos con filtros opcionales
     *
     * @param filtros Criterios de búsqueda
     * @return Página de resúmenes sísmicos
     */
    Page<SismoResumen> ejecutar(ResumenSismosFiltros filtros);

    /**
     * Filtros para resumen de sismos
     */
    record ResumenSismosFiltros(
        String codigoCentro,
        String codigoEstacion,
        LocalDateTime fechaDesde,
        LocalDateTime fechaHasta,
        Double magnitudMinima,
        Double magnitudMaxima,
        Pageable paginacion
    ) {}
}
