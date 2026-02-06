package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.CentroProduccionResponse;
import cr.go.ice.sismo_platform.application.port.in.CentroProduccionQuery;
import cr.go.ice.sismo_platform.domain.model.CentroProduccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/centros")
public class CentroController {

    private final CentroProduccionQuery query;

    public CentroController(CentroProduccionQuery query) {
        this.query = query;
    }

    @GetMapping
    public Page<CentroProduccionResponse> listar(
            @RequestParam(required = false) String nombre,
            Pageable pageable
    ) {
        return query.listarCentros(nombre, pageable).map(CentroController::toResponse);
    }

    private static CentroProduccionResponse toResponse(CentroProduccion centro) {
        return new CentroProduccionResponse(centro.codigo(), centro.nombre());
    }
}
