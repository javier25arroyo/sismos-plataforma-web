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
@Table(name = "Grupo_Despliegue", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDespliegueEntity {

    @Id
    @Column(name = "id_Región", nullable = false)
    private Short idRegion;

    @Column(name = "nombre_Región", length = 25)
    private String nombreRegion;

    @OneToMany(mappedBy = "grupoDespliegue")
    private List<CentroProduccionEntity> centros;
}
