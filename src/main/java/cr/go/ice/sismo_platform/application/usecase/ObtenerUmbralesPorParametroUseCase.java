package cr.go.ice.sismo_platform.application.usecase;

import cr.go.ice.sismo_platform.domain.model.Umbral;

import java.util.List;

/**
 * Caso de uso: Obtener umbrales por parámetro
 * Implementa la lógica de negocio para consulta de umbrales
 */
public interface ObtenerUmbralesPorParametroUseCase {

    /**
     * Obtiene todos los umbrales para un parámetro específico
     *
     * @param comando Comando con el parámetro a buscar
     * @return Lista de umbrales encontrados
     */
    List<Umbral> ejecutar(ObtenerUmbralesComando comando);

    /**
     * Comando para obtener umbrales
     */
    record ObtenerUmbralesComando(Integer codigoParametro) {}
}
