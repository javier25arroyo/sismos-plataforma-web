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
@Table(name = "Centro_Produccion", schema = "dbo")
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
}
