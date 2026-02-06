package cr.go.ice.sismo_platform.adapters.in.web.dto;

import java.time.LocalDateTime;

public record SismoResumenResponse(
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
