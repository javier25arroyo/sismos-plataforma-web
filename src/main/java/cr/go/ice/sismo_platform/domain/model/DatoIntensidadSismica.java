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
public class DatoIntensidadSismica {
    String codCentroPrd;
    String codEstacion;
    LocalDateTime feDato;
    Double vaAceleracion;
    Double vaDesplazamiento;
    LocalDateTime feLectura;
}
