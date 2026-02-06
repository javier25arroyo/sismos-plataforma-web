package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Param_Intensidad_Sismica", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroIntensidadSismicaEntity {

    @Id
    @Column(name = "Cod_Parametro", length = 3, nullable = false)
    private String codParametro;

    @Column(name = "Nom_Parametro", length = 50, nullable = false)
    private String nomParametro;

    @Column(name = "Unidad_Medida", length = 25)
    private String unidadMedida;

    @Column(name = "Simbolo_unidad", length = 6)
    private String simboloUnidad;
}
