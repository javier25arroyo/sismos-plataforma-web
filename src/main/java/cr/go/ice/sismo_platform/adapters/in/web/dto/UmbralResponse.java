package cr.go.ice.sismo_platform.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para umbrales por parámetro.
 */
@Schema(name = "UmbralResponse", description = "Representa un umbral de alerta para un parámetro sísmico")
public record UmbralResponse(
        @Schema(description = "Código del parámetro", example = "101")
        Integer codParametro,
        @Schema(description = "Código del umbral", example = "NIVEL 1")
        String codUmbral,
        @Schema(description = "Límite inferior del umbral", example = "0.15")
        Double limiteInferior,
        @Schema(description = "Mensaje asociado al umbral", example = "Alerta moderada")
        String mensaje,
        @Schema(description = "Color de visualización", example = "AMARILLO")
        String color
) {

}
