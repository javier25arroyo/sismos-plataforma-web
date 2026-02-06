package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record ReporteRepositorio(
        LocalDateTime feReporte,
        String titulo,
        String resumen,
        String url,
        String autor,
        byte[] archivo
) {}
