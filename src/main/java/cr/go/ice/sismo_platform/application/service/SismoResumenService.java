package cr.go.ice.sismo_platform.application.service;

import cr.go.ice.sismo_platform.application.port.in.SismoResumenQuery;
import cr.go.ice.sismo_platform.application.port.out.SismoResumenRepositoryPort;
import cr.go.ice.sismo_platform.application.usecase.ObtenerResumenSismosUseCase;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SismoResumenService implements SismoResumenQuery, ObtenerResumenSismosUseCase {

    private final SismoResumenRepositoryPort repository;

    public SismoResumenService(SismoResumenRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<SismoResumen> obtenerResumenUltimoSismo(
            String codCentroPrd,
            String codEstacion,
            LocalDateTime desde,
            LocalDateTime hasta,
            Double magnitudMin,
            Double magnitudMax,
            Pageable pageable
    ) {
        return repository.findResumenUltimoSismo(codCentroPrd, codEstacion, desde, hasta, magnitudMin, magnitudMax, pageable);
    }

    @Override
    public Page<SismoResumen> ejecutar(ResumenSismosFiltros filtros) {
        return obtenerResumenUltimoSismo(
                filtros.codigoCentro(),
                filtros.codigoEstacion(),
                filtros.fechaDesde(),
                filtros.fechaHasta(),
                filtros.magnitudMinima(),
                filtros.magnitudMaxima(),
                filtros.paginacion()
        );
    }
}
