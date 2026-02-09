package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record EventoSismico(
        LocalDateTime feSismo,
        Double magnitud,
        Double profundidad,
        String ubicacion,
        Double coordenadaX,
        Double coordenadaY,
        String urlFuente,
        byte[] mapa,
        String intensidades,
        String origen,
        Character sentido
) {}

