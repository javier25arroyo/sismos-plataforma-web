package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "Centro_Producción", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentroProduccionEntity {

    @Id
    @Column(name = "Cod_Centro_Prd", length = 2, nullable = false)
    private String codCentroPrd;

    @Column(name = "Nom_Centro_Prd", length = 30, nullable = false)
    private String nomCentroPrd;

    @Column(name = "CoordenadaX")
    private Double coordenadaX;

    @Column(name = "CoordenadaY")
    private Double coordenadaY;

    @Column(name = "Grupo_despliegue")
    private Short idRegion;

    @ManyToOne
    @JoinColumn(name = "Grupo_despliegue", referencedColumnName = "id_Región", insertable = false, updatable = false)
    private GrupoDespliegueEntity grupoDespliegue;

    @OneToMany(mappedBy = "centroProduccion")
    private List<EstacionEntity> estaciones;
}
