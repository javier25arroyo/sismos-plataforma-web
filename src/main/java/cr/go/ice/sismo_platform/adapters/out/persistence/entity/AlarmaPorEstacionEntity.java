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

@Entity
@Table(name = "Alarmas_Por_Estación", schema = "dbo")
@IdClass(AlarmaPorEstacionId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmaPorEstacionEntity {

    @Id
    @Column(name = "COD_CENTRO_PRD", length = 2, nullable = false)
    private String codCentroPrd;

    @Id
    @Column(name = "COD_ESTACION", length = 2, nullable = false)
    private String codEstacion;

    @Id
    @Column(name = "COD_PARAMETRO", nullable = false)
    private Integer codParametro;

    @Id
    @Column(name = "COD_UMBRAL", length = 10, nullable = false)
    private String codUmbral;

    @Column(name = "LIMITE_INFERIOR")
    private Double limiteInferior;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "COD_CENTRO_PRD", referencedColumnName = "Cod_Centro_Prd", insertable = false, updatable = false),
            @JoinColumn(name = "COD_ESTACION", referencedColumnName = "Cod_Estacion", insertable = false, updatable = false)
    })
    private EstacionEntity estacion;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "COD_PARAMETRO", referencedColumnName = "Cod_Parametro", insertable = false, updatable = false),
            @JoinColumn(name = "COD_UMBRAL", referencedColumnName = "Cod_umbral", insertable = false, updatable = false)
    })
    private UmbralEntity umbral;
}
