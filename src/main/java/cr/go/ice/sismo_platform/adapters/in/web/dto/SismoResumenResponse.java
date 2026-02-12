package cr.go.ice.sismo_platform.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * DTO de respuesta con el resumen de un sismo y su estación asociada.
 */
@Schema(name = "SismoResumenResponse", description = "Resumen del último sismo con datos de estación")
public record SismoResumenResponse(
        @Schema(description = "Código del centro de producción", example = "CB")
        String codCentroPrd,
        @Schema(description = "Nombre del centro de producción", example = "C.P. CARIBLANCO")
        String nomCentroPrd,
        @Schema(description = "Código de la estación", example = "BA")
        String codEstacion,
        @Schema(description = "Nombre de la estación", example = "Base")
        String nomEstacion,
        @Schema(description = "Fecha del dato de intensidad", example = "2024-03-18T12:30:00")
        LocalDateTime feDato,
        @Schema(description = "Valor de aceleración")
        Double vaAceleracion,
        @Schema(description = "Valor de desplazamiento")
        Double vaDesplazamiento,
        @Schema(description = "Fecha y hora del sismo", example = "2024-03-18T12:00:00")
        LocalDateTime feSismo,
        @Schema(description = "Magnitud del sismo", example = "5.2")
        Double magnitud,
        @Schema(description = "Profundidad del sismo", example = "12.5")
        Double profundidad,
        @Schema(description = "Ubicación del sismo", example = "Noreste de San José")
        String ubicacion,
        @Schema(description = "Coordenada X del sismo")
        Double coordenadaX,
        @Schema(description = "Coordenada Y del sismo")
        Double coordenadaY
) {}
