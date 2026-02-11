package cr.go.ice.sismo_platform.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para una estación sísmica.
 */
@Schema(name = "EstacionResponse", description = "Representa una estación sísmica resumida")
public record EstacionResponse(
        @Schema(description = "Código del centro de producción", example = "CB")
        String codCentroPrd,
        @Schema(description = "Código de la estación", example = "BA")
        String codEstacion,
        @Schema(description = "Nombre de la estación", example = "Base")
        String nomEstacion,
        @Schema(description = "Coordenada X (longitud)")
        Double coordenadaX,
        @Schema(description = "Coordenada Y (latitud)")
        Double coordenadaY
) {}
