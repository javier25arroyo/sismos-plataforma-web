package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.adapters.in.web.dto.UmbralResponse;
import cr.go.ice.sismo_platform.application.port.in.UmbralQuery;
import cr.go.ice.sismo_platform.domain.model.Umbral;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/umbrales")
public class UmbralController {

    private final UmbralQuery query;

    public UmbralController(UmbralQuery query) {
        this.query = query;
    }

    @GetMapping("/{codParametro}")
    public List<UmbralResponse> listar(@PathVariable String codParametro) {
        return query.listarPorParametro(codParametro).stream().map(UmbralController::toResponse).toList();
    }

    private static UmbralResponse toResponse(Umbral umbral) {
        return new UmbralResponse(
                umbral.codParametro(),
                umbral.codUmbral(),
                umbral.valor(),
                umbral.mensaje(),
                umbral.color()
        );
    }
}
