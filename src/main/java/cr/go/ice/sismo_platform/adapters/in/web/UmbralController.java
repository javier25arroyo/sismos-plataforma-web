package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.UmbralResponse;
import cr.go.ice.sismo_platform.application.usecase.ObtenerUmbralesPorParametroUseCase;
import cr.go.ice.sismo_platform.application.usecase.ObtenerUmbralesPorParametroUseCase.ObtenerUmbralesComando;
import cr.go.ice.sismo_platform.domain.model.Umbral;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
