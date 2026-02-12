package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
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
@Table(name = "Estaciones", schema = "dbo")
@IdClass(EstacionId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstacionEntity {

    @Id
    @Column(name = "Cod_Centro_Prd", length = 2, nullable = false)
    private String codCentroPrd;

    @Id
    @Column(name = "Cod_Estacion", length = 2, nullable = false)
    private String codEstacion;

    @Column(name = "Nom_Estacion", length = 30, nullable = false)
    private String nomEstacion;

    @Column(name = "CoordenadaX")
    private Double coordenadaX;

    @Column(name = "CoordenadaY")
    private Double coordenadaY;

    @ManyToOne
    @JoinColumn(name = "Cod_Centro_Prd", referencedColumnName = "Cod_Centro_Prd", insertable = false, updatable = false)
    private CentroProduccionEntity centroProduccion;

    @OneToMany(mappedBy = "estacion")
    private List<DatoIntensidadSismicaEntity> datos;

    @OneToMany(mappedBy = "estacion")
    private List<AlarmaPorEstacionEntity> alarmas;
}
