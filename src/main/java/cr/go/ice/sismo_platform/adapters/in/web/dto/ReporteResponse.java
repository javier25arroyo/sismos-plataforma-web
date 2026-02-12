package cr.go.ice.sismo_platform.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para reportes del repositorio.
 */
@Schema(name = "ReporteResponse", description = "Representa un reporte resumido del repositorio")
public record ReporteResponse(
        @Schema(description = "Fecha del reporte", example = "2024-03-18T12:00:00")
        LocalDateTime fecha,
        @Schema(description = "Título del reporte", example = "Informe de actividad sísmica")
        String titulo,
        @Schema(description = "Resumen del reporte")
        String resumen,
        @Schema(description = "URL del reporte", example = "https://example.com/reporte.pdf")
        String url,
        @Schema(description = "Autor del reporte", example = "RSN")
        String autor
) {}
