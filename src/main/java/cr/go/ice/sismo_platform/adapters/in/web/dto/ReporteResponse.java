package cr.go.ice.sismo_platform.adapters.in.web.dto;

import java.time.LocalDateTime;

public record ReporteResponse(
        LocalDateTime fecha,
        String titulo,
        String resumen,
        String url,
        String autor
) {}
