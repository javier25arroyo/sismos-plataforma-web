package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.SismoResumenResponse;
import cr.go.ice.sismo_platform.application.port.in.SismoResumenQuery;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
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
public class SismoController {

    private final SismoResumenQuery query;

    public SismoController(SismoResumenQuery query) {
        this.query = query;
    }

    @GetMapping("/ultimo/resumen")
    public Page<SismoResumenResponse> resumenUltimoSismo(
            @RequestParam(required = false) String codCentroPrd,
            @RequestParam(required = false) String codEstacion,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            @RequestParam(required = false) Double magnitudMin,
            @RequestParam(required = false) Double magnitudMax,
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
