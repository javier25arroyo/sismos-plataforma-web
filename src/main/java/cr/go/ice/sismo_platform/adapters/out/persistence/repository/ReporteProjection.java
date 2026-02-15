package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import java.time.LocalDateTime;

public interface ReporteProjection {
    LocalDateTime getFeReporte();
    String getTituloReporte();
    String getResumen();
    String getUrl();
    String getAutor();
    byte[] getArchivo();
}
