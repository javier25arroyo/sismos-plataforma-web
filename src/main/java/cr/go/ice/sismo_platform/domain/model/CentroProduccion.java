package cr.go.ice.sismo_platform.domain.model;

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
public class CentroProduccion {
    String codCentroPrd;
    String nomCentroPrd;
    Double coordenadaX;
    Double coordenadaY;
    Short grupoDespliegue;
}
