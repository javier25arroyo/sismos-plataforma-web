package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlarmaPorEstacionId implements Serializable {
    private String codCentroPrd;
    private String codEstacion;
    private Integer codParametro;
    private String codUmbral;
}
