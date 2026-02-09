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
public class EventoSismico {
    LocalDateTime feSismo;
    Double magnitud;
    Double profundidad;
    String ubicacion;
    Double coordenadaX;
    Double coordenadaY;
    String urlFuente;
    byte[] mapa;
    String intensidades;
    String origen;
    Character sentido;
}
