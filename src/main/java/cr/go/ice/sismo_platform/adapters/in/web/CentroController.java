package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.CentroProduccionResponse;
import cr.go.ice.sismo_platform.application.port.in.CentroProduccionQuery;
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

@RestController
@RequestMapping("/api/centros")
@Tag(name = "Centros de Producción")
public class CentroController {

    private final CentroProduccionQuery query;

    public CentroController(CentroProduccionQuery query) {
        this.query = query;
    }

    @GetMapping
    @Operation(summary = "Lista centros de producción con paginación y filtros")
    public Page<CentroProduccionResponse> listar(
            @Parameter(description = "Filtra por prefijo del código del centro") @RequestParam(required = false) String codigo,
            @Parameter(description = "Filtra por nombre del centro (contiene)") @RequestParam(required = false) String nombre,
            Pageable pageable
    ) {
        return query.listarCentros(codigo, nombre, pageable).map(CentroController::toResponse);
    }

    private static CentroProduccionResponse toResponse(CentroProduccion centro) {
        return new CentroProduccionResponse(centro.codigo(), centro.nombre());
    }
}
