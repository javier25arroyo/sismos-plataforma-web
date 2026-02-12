package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.UmbralResponse;
import cr.go.ice.sismo_platform.application.usecase.ObtenerUmbralesPorParametroUseCase;
import cr.go.ice.sismo_platform.application.usecase.ObtenerUmbralesPorParametroUseCase.ObtenerUmbralesComando;
import cr.go.ice.sismo_platform.domain.model.Umbral;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para consulta de umbrales de parámetros sísmicos.
 */
@RestController
@RequestMapping("/api/umbrales")
@Tag(name = "Umbrales")
public class UmbralController {

    private final ObtenerUmbralesPorParametroUseCase obtenerUmbralesUseCase;

    public UmbralController(ObtenerUmbralesPorParametroUseCase obtenerUmbralesUseCase) {
        this.obtenerUmbralesUseCase = obtenerUmbralesUseCase;
    }

    @GetMapping("/{codParametro}")
    @Operation(summary = "Obtiene umbrales por parámetro específico")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de umbrales",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Umbrales",
                                    summary = "Lista de umbrales",
                                    value = """
                                            [
                                              {
                                                "codParametro": 101,
                                                "codUmbral": "NIVEL 1",
                                                "limiteInferior": 0.15,
                                                "mensaje": "Alerta moderada",
                                                "color": "AMARILLO"
                                              },
                                              {
                                                "codParametro": 101,
                                                "codUmbral": "NIVEL 2",
                                                "limiteInferior": 0.30,
                                                "mensaje": "Alerta alta",
                                                "color": "ROJO"
                                              }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Código de parámetro inválido",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parámetro no encontrado",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public List<UmbralResponse> listar(@Parameter(description = "Código del parámetro") @PathVariable Integer codParametro) {
        var comando = new ObtenerUmbralesComando(codParametro);
        return obtenerUmbralesUseCase.ejecutar(comando).stream()
                .map(UmbralController::toResponse)
                .toList();
    }

    private static UmbralResponse toResponse(Umbral umbral) {
        return new UmbralResponse(
                umbral.codParametro(),
                umbral.codUmbral(),
                umbral.limiteInferior(),
                umbral.mensaje(),
                umbral.color()
        );
    }
}
