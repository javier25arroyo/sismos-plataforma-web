package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record DatoIntensidadSismica(
        String codCentro,
        String codEstacion,
        LocalDateTime feDato,
        Double vaAceleracion,
        Double vaDesplazamiento,
        LocalDateTime feLectura
) {}
