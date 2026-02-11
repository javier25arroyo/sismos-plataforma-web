package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.SismoResumenResponse;
import cr.go.ice.sismo_platform.application.port.in.SismoResumenQuery;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
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
 * Controlador REST para consultas de sismos.
 * Expones resúmenes filtrados del último evento registrado.
 */
@RestController
@RequestMapping("/api/sismos")
@Tag(name = "Sismos")
public class SismoController {

    private final SismoResumenQuery query;

    public SismoController(SismoResumenQuery query) {
        this.query = query;
    }

    @GetMapping("/ultimo/resumen")
    @Operation(
            summary = "Resumen del último sismo con filtros y paginación",
            description = "Retorna un resumen paginado del último sismo registrado y sus estaciones asociadas."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Resumen paginado del último sismo",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "ResumenUltimoSismo",
                                    summary = "Respuesta paginada",
                                    value = """
                                            {
                                              "content": [
                                                {
                                                  "codCentroPrd": "CB",
                                                  "nomCentroPrd": "C.P. CARIBLANCO",
                                                  "codEstacion": "BA",
                                                  "nomEstacion": "Base",
                                                  "feDato": "2024-03-18T12:30:00",
                                                  "vaAceleracion": 0.12,
                                                  "vaDesplazamiento": 1.2,
                                                  "feSismo": "2024-03-18T12:00:00",
                                                  "magnitud": 5.2,
                                                  "profundidad": 12.5,
                                                  "ubicacion": "Noreste de San José",
                                                  "coordenadaX": -84.1,
                                                  "coordenadaY": 9.9
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
