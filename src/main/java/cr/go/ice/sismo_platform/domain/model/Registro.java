package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record Registro(
        Integer codRegistro,
        String tituloRegistro,
        LocalDateTime feRegistro,
        String nomRegistro,
        Double elevacionEstacion,
        String tipoSuelo,
        String condSitio,
        Double azimut,
        Double deltaT,
        Double canal1,
        Double canal2,
        Double canal3,
        Double freqMinFilt,
        Double freqMaxFilt,
        Integer numPuntos,
        Double distEpicentral,
        Double distHipocentral
) {}

