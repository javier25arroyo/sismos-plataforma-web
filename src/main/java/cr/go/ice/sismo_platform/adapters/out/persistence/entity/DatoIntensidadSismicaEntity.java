package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Dat_Intensidad_Sísmica", schema = "dbo")
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

    @Column(name = "Va_Aceleración")
    private Double vaAceleracion;

    @Column(name = "Va_Desplazamiento")
    private Double vaDesplazamiento;

    @Column(name = "fe_lectura")
    private LocalDateTime feLectura;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Cod_Centro_Prd", referencedColumnName = "Cod_Centro_Prd", insertable = false, updatable = false),
            @JoinColumn(name = "Cod_Estacion", referencedColumnName = "Cod_Estacion", insertable = false, updatable = false)
    })
    private EstacionEntity estacion;
}
