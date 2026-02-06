package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record SismoResumen(
        String codCentroPrd,
        String nomCentroPrd,
        String codEstacion,
        String nomEstacion,
        LocalDateTime feDato,
        Double vaAceleracion,
        Double vaDesplazamiento,
        LocalDateTime feSismo,
        Double magnitud,
        Double profundidad,
        String ubicacion,
        Double coordenadaX,
        Double coordenadaY
) {}
