package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.CentroProduccionResponse;
import cr.go.ice.sismo_platform.application.usecase.BuscarCentrosProduccionUseCase;
import cr.go.ice.sismo_platform.application.usecase.BuscarCentrosProduccionUseCase.BuscarCentrosFiltros;
import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador REST para gestionar los Centros de Producción.
 * Proporciona endpoints para la búsqueda y listado de centros con soporte para paginación y filtrado.
 */
@RestController
@RequestMapping("/api/centros")
@Tag(name = "Centros de Producción", description = "Endpoints para la gestión de centros de producción")
public class CentroController {

    private final BuscarCentrosProduccionUseCase buscarCentrosUseCase;

    public CentroController(BuscarCentrosProduccionUseCase buscarCentrosUseCase) {
        this.buscarCentrosUseCase = buscarCentrosUseCase;
    }

    /**
     * Lista los centros de producción aplicando filtros opcionales y paginación.
     *
     * @param codigo   Prefijo opcional del código del centro para filtrar.
     * @param nombre   Nombre o parte del nombre del centro para filtrar (búsqueda parcial).
     * @param pageable Configuración de paginación (page, size, sort).
     * @return Una página de objetos {@link CentroProduccionResponse}.
     */
    @GetMapping
    @Operation(
        summary = "Lista centros de producción",
        description = "Retorna una lista paginada de centros de producción. Permite filtrar por prefijo de código y por nombre."
    )
    public Page<CentroProduccionResponse> listar(
            @Parameter(description = "Filtra por prefijo del código del centro (ej. 'CH')") 
            @RequestParam(required = false) String codigo,
            
            @Parameter(description = "Filtra por nombre del centro (búsqueda parcial, insensible a mayúsculas/minúsculas)") 
            @RequestParam(required = false) String nombre,
            
            @Parameter(description = "Parámetros de paginación y ordenamiento") 
            Pageable pageable
    ) {
        var filtros = new BuscarCentrosFiltros(codigo, nombre, pageable);
        return buscarCentrosUseCase.ejecutar(filtros).map(CentroController::toResponse);
    }

    private static CentroProduccionResponse toResponse(CentroProduccion centro) {
        return new CentroProduccionResponse(centro.codCentroPrd(), centro.nomCentroPrd());
    }
}
