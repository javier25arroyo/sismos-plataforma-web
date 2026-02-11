package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.ReporteResponse;
import cr.go.ice.sismo_platform.application.port.in.ReporteQuery;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Controlador REST para consulta de reportes.
 * Proporciona endpoints de listado con filtros y paginación.
 */
@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Reportes")
public class ReporteController {

    private final ReporteQuery query;

    public ReporteController(ReporteQuery query) {
        this.query = query;
    }

    @GetMapping
    @Operation(
            summary = "Lista reportes con paginación y filtros",
            description = "Retorna una lista paginada de reportes con filtros opcionales por título, autor y rango de fechas."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado paginado de reportes",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "ReportesPaginados",
                                    summary = "Respuesta paginada",
                                    value = """
                                            {
                                              "content": [
                                                {
                                                  "fecha": "2024-03-18T12:00:00",
                                                  "titulo": "Informe de actividad sísmica",
                                                  "resumen": "Resumen del reporte",
                                                  "url": "https://example.com/reporte.pdf",
                                                  "autor": "RSN"
                                                }
                                              ],
                                              "pageable": { "pageNumber": 0, "pageSize": 10 },
                                              "totalElements": 1,
                                              "totalPages": 1,
                                              "last": true,
                                              "size": 10,
                                              "number": 0,
                                              "sort": { "sorted": false, "unsorted": true, "empty": true },
                                              "first": true,
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
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
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
                reporte.tituloReporte(),
                reporte.resumen(),
                reporte.url(),
                reporte.autor()
        );
    }
}
