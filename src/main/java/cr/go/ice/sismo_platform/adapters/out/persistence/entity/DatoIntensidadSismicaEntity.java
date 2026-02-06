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

import java.time.LocalDateTime;

@Entity
@Table(name = "Dat_Instensidad_Sismica", schema = "dbo")
@IdClass(DatoIntensidadSismicaId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatoIntensidadSismicaEntity {

    @Id
    @Column(name = "Cod_Centro_Prd", length = 2, nullable = false)
    private String codCentroPrd;

    @Id
    @Column(name = "Cod_Estacion", length = 2, nullable = false)
    private String codEstacion;

    @Id
    @Column(name = "Fe_dato", nullable = false)
    private LocalDateTime feDato;

    @Column(name = "Va_Aceleracion", nullable = false)
    private Double vaAceleracion;

    @Column(name = "Va_Desplazamiento", nullable = false)
    private Double vaDesplazamiento;

    @Column(name = "fe_lectura")
    private LocalDateTime feLectura;
}
