package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
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
}
