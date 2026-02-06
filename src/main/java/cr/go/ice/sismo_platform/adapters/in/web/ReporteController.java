package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.ReporteResponse;
import cr.go.ice.sismo_platform.application.port.in.ReporteQuery;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
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
@RequestMapping("/api/reportes")
@Tag(name = "Reportes")
public class ReporteController {

    private final ReporteQuery query;

    public ReporteController(ReporteQuery query) {
        this.query = query;
    }

    @GetMapping
    @Operation(summary = "Lista reportes con paginación y filtros")
    public Page<ReporteResponse> listar(
            @Parameter(description = "Filtra por título (contiene)") @RequestParam(required = false) String titulo,
            @Parameter(description = "Filtra por autor (contiene)") @RequestParam(required = false) String autor,
            @Parameter(description = "Fecha desde (ISO-8601)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @Parameter(description = "Fecha hasta (ISO-8601)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
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
