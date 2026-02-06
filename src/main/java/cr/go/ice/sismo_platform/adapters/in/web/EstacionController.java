package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.EstacionResponse;
import cr.go.ice.sismo_platform.application.port.in.EstacionQuery;
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

@RestController
@RequestMapping("/api/centros/{codigoCentro}/estaciones")
@Tag(name = "Estaciones")
public class EstacionController {

    private final EstacionQuery query;

    public EstacionController(EstacionQuery query) {
        this.query = query;
    }

    @GetMapping
    @Operation(summary = "Lista estaciones por centro con paginación y filtros")
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
        return query.listarPorCentro(codigoCentro, codigoEstacion, nombre, minX, maxX, minY, maxY, pageable)
                .map(EstacionController::toResponse);
    }

    private static EstacionResponse toResponse(Estacion estacion) {
        return new EstacionResponse(
                estacion.codigoCentro(),
                estacion.codigoEstacion(),
                estacion.nombre(),
                estacion.coordenadaX(),
                estacion.coordenadaY()
        );
    }
}
