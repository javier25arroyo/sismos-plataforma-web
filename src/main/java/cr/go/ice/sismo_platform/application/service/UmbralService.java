package cr.go.ice.sismo_platform.application.service;

import cr.go.ice.sismo_platform.application.port.in.UmbralQuery;
import cr.go.ice.sismo_platform.application.port.out.UmbralRepositoryPort;
import cr.go.ice.sismo_platform.domain.model.Umbral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmbralService implements UmbralQuery {

    private final UmbralRepositoryPort repository;

    public UmbralService(UmbralRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Umbral> listarPorParametro(Integer codParametro) {
        return repository.findByParametro(codParametro.toString());
    }
}
