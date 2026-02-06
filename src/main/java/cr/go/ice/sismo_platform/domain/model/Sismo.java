package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record Sismo(
        LocalDateTime fechaHora,
        Double magnitud,
        Double profundidad,
        String ubicacion,
        Double coordenadaX,
        Double coordenadaY,
        String urlFuente,
        byte[] mapa
) {}
