package cr.go.ice.sismo_platform.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para un centro de producción.
 */
@Schema(name = "CentroProduccionResponse", description = "Representa un centro de producción resumido")
public record CentroProduccionResponse(
        @Schema(description = "Código del centro de producción", example = "CB")
        String codCentroPrd,
        @Schema(description = "Nombre del centro de producción", example = "C.P. CARIBLANCO")
        String nomCentroPrd
) {}
