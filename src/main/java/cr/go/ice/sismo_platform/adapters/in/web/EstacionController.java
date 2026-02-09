package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.EstacionResponse;
import cr.go.ice.sismo_platform.application.usecase.BuscarEstacionesPorCentroUseCase;
import cr.go.ice.sismo_platform.application.usecase.BuscarEstacionesPorCentroUseCase.BuscarEstacionesFiltros;
import cr.go.ice.sismo_platform.domain.model.Estacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador REST para gestionar operaciones relacionadas con las Estaciones.
 * Proporciona endpoints para listar estaciones filtradas por centro de producción.
 */
@RestController
@RequestMapping("/api/centros/{codigoCentro}/estaciones")
@Tag(name = "Estaciones", description = "Endpoints para la gestión de estaciones sismológicas")
public class EstacionController {

    private final BuscarEstacionesPorCentroUseCase buscarEstacionesUseCase;

    public EstacionController(BuscarEstacionesPorCentroUseCase buscarEstacionesUseCase) {
        this.buscarEstacionesUseCase = buscarEstacionesUseCase;
    }

    /**
     * Lista las estaciones asociadas a un centro de producción específico.
     * Permite filtrado por código, nombre y coordenadas geográficas.
     *
     * @param codigoCentro   Código del centro de producción.
     * @param codigoEstacion Filtro opcional por código de estación.
     * @param nombre         Filtro opcional por nombre de estación.
     * @param minX           Coordenada X mínima.
     * @param maxX           Coordenada X máxima.
     * @param minY           Coordenada Y mínima.
     * @param maxY           Coordenada Y máxima.
     * @param pageable       Información de paginación.
     * @return Página de estaciones que cumplen con los criterios.
     */
    @GetMapping
    @Operation(summary = "Lista estaciones por centro con paginación y filtros", 
               description = "Retorna una página de estaciones pertenecientes al centro de producción especificado.")
    public Page<EstacionResponse> listar(
            @PathVariable String codigoCentro,
            @Parameter(description = "Filtra por código de estación exacto") @RequestParam(required = false) String codigoEstacion,
            @Parameter(description = "Filtra por nombre (contiene)") @RequestParam(required = false) String nombre,
            @Parameter(description = "Coordenada X mínima") @RequestParam(required = false) Double minX,
            @Parameter(description = "Coordenada X máxima") @RequestParam(required = false) Double maxX,
            @Parameter(description = "Coordenada Y mínima") @RequestParam(required = false) Double minY,
            @Parameter(description = "Coordenada Y máxima") @RequestParam(required = false) Double maxY,
            Pageable pageable
    ) {
        var filtros = new BuscarEstacionesFiltros(
            codigoCentro, codigoEstacion, nombre, minX, maxX, minY, maxY, pageable);
        return buscarEstacionesUseCase.ejecutar(filtros).map(EstacionController::toResponse);
    }

    private static EstacionResponse toResponse(Estacion estacion) {
        return new EstacionResponse(
                estacion.codCentroPrd(),
                estacion.codEstacion(),
                estacion.nomEstacion(),
                estacion.coordenadaX(),
                estacion.coordenadaY()
        );
    }
}
