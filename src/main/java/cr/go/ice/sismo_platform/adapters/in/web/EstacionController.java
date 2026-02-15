package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.EstacionResponse;
import cr.go.ice.sismo_platform.adapters.in.web.dto.PageResponse;
import cr.go.ice.sismo_platform.application.usecase.BuscarEstacionesPorCentroUseCase;
import cr.go.ice.sismo_platform.application.usecase.BuscarEstacionesPorCentroUseCase.BuscarEstacionesFiltros;
import cr.go.ice.sismo_platform.domain.model.Estacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado paginado de estaciones",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "EstacionesPaginadas",
                                    summary = "Respuesta paginada",
                                    value = """
                                            {
                                              "content": [
                                                {
                                                  "codCentroPrd": "CB",
                                                  "codEstacion": "BA",
                                                  "nomEstacion": "Base",
                                                  "coordenadaX": -84.178,
                                                  "coordenadaY": 10.309
                                                }
                                              ],
                                              "page": 0,
                                              "size": 10,
                                              "totalElements": 1,
                                              "totalPages": 1,
                                              "first": true,
                                              "last": true,
                                              "numberOfElements": 1,
                                              "empty": false
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parámetros de filtro o paginación inválidos",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Centro de producción no encontrado",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public PageResponse<EstacionResponse> listar(
            @Parameter(description = "Código del centro de producción") @PathVariable("codigoCentro") String codigoCentro,
            @Parameter(description = "Filtra por código de estación exacto") @RequestParam(name = "codigoEstacion", required = false) String codigoEstacion,
            @Parameter(description = "Filtra por nombre (contiene)") @RequestParam(name = "nombre", required = false) String nombre,
            @Parameter(description = "Coordenada X mínima") @RequestParam(name = "minX", required = false) Double minX,
            @Parameter(description = "Coordenada X máxima") @RequestParam(name = "maxX", required = false) Double maxX,
            @Parameter(description = "Coordenada Y mínima") @RequestParam(name = "minY", required = false) Double minY,
            @Parameter(description = "Coordenada Y máxima") @RequestParam(name = "maxY", required = false) Double maxY,
            Pageable pageable
    ) {
        var filtros = new BuscarEstacionesFiltros(
            codigoCentro, codigoEstacion, nombre, minX, maxX, minY, maxY, pageable);
        var result = buscarEstacionesUseCase.ejecutar(filtros).map(EstacionController::toResponse);
        return PageResponse.from(result);
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
