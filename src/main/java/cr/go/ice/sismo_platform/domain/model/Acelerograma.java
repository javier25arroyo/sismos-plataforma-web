package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record Acelerograma(
        LocalDateTime feTerremoto,
        String coEstacion,
        String canal,
        String sitio,
        String instrumento,
        Double timeSeg,
        Double aceleracion
) {}

