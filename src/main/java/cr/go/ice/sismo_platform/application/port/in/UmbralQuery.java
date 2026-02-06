package cr.go.ice.sismo_platform.application.port.in;

import cr.go.ice.sismo_platform.domain.model.Umbral;

import java.util.List;

public interface UmbralQuery {
    List<Umbral> listarPorParametro(String codParametro);
}
