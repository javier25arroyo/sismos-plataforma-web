package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DatoIntensidadSismicaId implements Serializable {
    private String codCentroPrd;
    private String codEstacion;
    private LocalDateTime feDato;
}
