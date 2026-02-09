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
public class Estacion {
    String codCentroPrd;
    String codEstacion;
    String nomEstacion;
    Double coordenadaX;
    Double coordenadaY;
    String nuSerie;
    Short idCanal;
    String tipoInstrumento;
    Double eleEstacion;
    String tipoSuelo;
    String condiSitio;
    Double deltaT;
    Double azimut;
    String chn1Long;
    String chn2Vert;
    String chn3Trans;
    Double bitWeightCh1;
    Double bitWeightCh2;
    Double bitWeightCh3;
    Double vpuCh1;
    Double vpuCh2;
    Double vpuCh3;
    String modInst;
}
