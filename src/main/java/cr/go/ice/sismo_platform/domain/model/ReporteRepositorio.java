package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record ReporteRepositorio(
        LocalDateTime feReporte,
        String tituloReporte,
        String resumen,
        String url,
        String autor,
        byte[] archivo
) {}
