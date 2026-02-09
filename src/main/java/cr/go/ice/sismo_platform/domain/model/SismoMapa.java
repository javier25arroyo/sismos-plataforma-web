package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record SismoMapa(
        LocalDateTime feSismo,
        Double magnitud,
        byte[] mapa
) {}

