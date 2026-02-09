package cr.go.ice.sismo_platform.application.usecase.impl;

import cr.go.ice.sismo_platform.application.port.out.UmbralRepositoryPort;
import cr.go.ice.sismo_platform.application.usecase.ObtenerUmbralesPorParametroUseCase;
import cr.go.ice.sismo_platform.domain.model.Umbral;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del caso de uso: Obtener umbrales por parámetro
 */
@Service
public class ObtenerUmbralesPorParametroUseCaseImpl implements ObtenerUmbralesPorParametroUseCase {

    private final UmbralRepositoryPort repositoryPort;

    public ObtenerUmbralesPorParametroUseCaseImpl(UmbralRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Umbral> ejecutar(ObtenerUmbralesComando comando) {
        validarComando(comando);

        return repositoryPort.findByParametro(comando.codigoParametro().toString());
    }

    private void validarComando(ObtenerUmbralesComando comando) {
        if (comando == null) {
            throw new IllegalArgumentException("El comando no puede ser nulo");
        }
        if (comando.codigoParametro() == null) {
            throw new IllegalArgumentException("El código del parámetro no puede ser nulo");
        }
        if (comando.codigoParametro() <= 0) {
            throw new IllegalArgumentException("El código del parámetro debe ser mayor que cero");
        }
    }
}
