package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import java.time.LocalDateTime;

public interface SismoResumenProjection {
    String getCodCentroPrd();
    String getNomCentroPrd();
    String getCodEstacion();
    String getNomEstacion();
    LocalDateTime getFeDato();
    Double getVaAceleracion();
    Double getVaDesplazamiento();
    LocalDateTime getFeSismo();
    Double getMagnitud();
    Double getProfundidad();
    String getUbicacion();
    Double getCoordenadaX();
    Double getCoordenadaY();
}
