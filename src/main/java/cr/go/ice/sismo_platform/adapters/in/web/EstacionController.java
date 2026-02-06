package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.EstacionResponse;
import cr.go.ice.sismo_platform.application.port.in.EstacionQuery;
import cr.go.ice.sismo_platform.domain.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/centros/{codigoCentro}/estaciones")
public class EstacionController {

    private final EstacionQuery query;

    public EstacionController(EstacionQuery query) {
        this.query = query;
    }

    @GetMapping
    public Page<EstacionResponse> listar(
            @PathVariable String codigoCentro,
            @RequestParam(required = false) String nombre,
            Pageable pageable
    ) {
        return query.listarPorCentro(codigoCentro, nombre, pageable).map(EstacionController::toResponse);
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
