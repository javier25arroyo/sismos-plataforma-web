package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.converter.NivelUmbralConverter;
import cr.go.ice.sismo_platform.domain.model.NivelUmbral;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Umbrales", schema = "dbo")
@IdClass(UmbralId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UmbralEntity {

    @Id
    @Column(name = "Cod_Parametro", nullable = false)
    private Integer codParametro;

    @Id
    @Column(name = "Cod_umbral", length = 10, nullable = false)
    @Convert(converter = NivelUmbralConverter.class)
    private NivelUmbral codUmbral;

    @Column(name = "limite_inferior")
    private Double limiteInferior;

    @Column(name = "mensaje", length = 255)
    private String mensaje;

    @Column(name = "color", length = 10)
    private String color;

    @ManyToOne
    @JoinColumn(name = "Cod_Parametro", referencedColumnName = "Cod_Parametro", insertable = false, updatable = false)
    private ParametroIntensidadSismicaEntity parametro;

    @OneToMany(mappedBy = "umbral")
    private List<AlarmaPorEstacionEntity> alarmas;
}
