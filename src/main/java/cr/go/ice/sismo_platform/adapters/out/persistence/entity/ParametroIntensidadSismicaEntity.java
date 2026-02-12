package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Param_Intensidad_Sísmica", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroIntensidadSismicaEntity {

    @Id
    @Column(name = "Cod_Parametro", nullable = false)
    private Integer codParametro;

    @Column(name = "Nom_Parametro", length = 50)
    private String nomParametro;

    @Column(name = "Unidad_Medida", length = 25, nullable = false)
    private String unidadMedida;

    @Column(name = "Símbolo_unidad", length = 6, nullable = false)
    private String simboloUnidad;

    @OneToMany(mappedBy = "parametro")
    private List<UmbralEntity> umbrales;
}
