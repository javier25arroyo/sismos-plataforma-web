package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.ReporteResponse;
import cr.go.ice.sismo_platform.application.port.in.ReporteQuery;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteQuery query;

    public ReporteController(ReporteQuery query) {
        this.query = query;
    }

    @GetMapping
    public Page<ReporteResponse> listar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            Pageable pageable
    ) {
        return query.listarReportes(titulo, autor, desde, hasta, pageable).map(ReporteController::toResponse);
    }

    private static ReporteResponse toResponse(ReporteRepositorio reporte) {
        return new ReporteResponse(
                reporte.feReporte(),
                reporte.titulo(),
                reporte.resumen(),
                reporte.url(),
                reporte.autor()
        );
    }
}
