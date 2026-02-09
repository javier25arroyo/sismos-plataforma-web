package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(fluent = true)
public class Registro {
    Integer codRegistro;
    String tituloRegistro;
    LocalDateTime feRegistro;
    String nomRegistro;
    Double elevacionEstacion;
    String tipoSuelo;
    String condSitio;
    Double azimut;
    Double deltaT;
    Double canal1;
    Double canal2;
    Double canal3;
    Double freqMinFilt;
    Double freqMaxFilt;
    Integer numPuntos;
    Double distEpicentral;
    Double distHipocentral;
}
