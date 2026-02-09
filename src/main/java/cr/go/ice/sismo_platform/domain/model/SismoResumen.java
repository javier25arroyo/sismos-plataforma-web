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
public class SismoResumen {
    String codCentroPrd;
    String nomCentroPrd;
    String codEstacion;
    String nomEstacion;
    LocalDateTime feDato;
    Double vaAceleracion;
    Double vaDesplazamiento;
    LocalDateTime feSismo;
    Double magnitud;
    Double profundidad;
    String ubicacion;
    Double coordenadaX;
    Double coordenadaY;
}
