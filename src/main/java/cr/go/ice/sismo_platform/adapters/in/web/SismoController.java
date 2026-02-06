package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.SismoResumenResponse;
import cr.go.ice.sismo_platform.application.port.in.SismoResumenQuery;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sismos")
@Tag(name = "Sismos")
public class SismoController {

    private final SismoResumenQuery query;

    public SismoController(SismoResumenQuery query) {
        this.query = query;
    }

    @GetMapping("/ultimo/resumen")
    @Operation(summary = "Resumen del último sismo con filtros y paginación")
    public Page<SismoResumenResponse> resumenUltimoSismo(
            @Parameter(description = "Código del centro de producción") @RequestParam(required = false) String codCentroPrd,
            @Parameter(description = "Código de estación") @RequestParam(required = false) String codEstacion,
            @Parameter(description = "Fecha desde (ISO-8601)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @Parameter(description = "Fecha hasta (ISO-8601)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            @Parameter(description = "Magnitud mínima") @RequestParam(required = false) Double magnitudMin,
            @Parameter(description = "Magnitud máxima") @RequestParam(required = false) Double magnitudMax,
            Pageable pageable
    ) {
        return query.obtenerResumenUltimoSismo(codCentroPrd, codEstacion, desde, hasta, magnitudMin, magnitudMax, pageable)
                .map(SismoController::toResponse);
    }

    private static SismoResumenResponse toResponse(SismoResumen resumen) {
        return new SismoResumenResponse(
                resumen.codCentroPrd(),
                resumen.nomCentroPrd(),
                resumen.codEstacion(),
                resumen.nomEstacion(),
                resumen.feDato(),
                resumen.vaAceleracion(),
                resumen.vaDesplazamiento(),
                resumen.feSismo(),
                resumen.magnitud(),
                resumen.profundidad(),
                resumen.ubicacion(),
                resumen.coordenadaX(),
                resumen.coordenadaY()
        );
    }
}
