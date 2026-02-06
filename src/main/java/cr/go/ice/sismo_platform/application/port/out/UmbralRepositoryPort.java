package cr.go.ice.sismo_platform.application.port.out;

import cr.go.ice.sismo_platform.domain.model.Umbral;

import java.util.List;

public interface UmbralRepositoryPort {
    List<Umbral> findByParametro(String codParametro);
}
