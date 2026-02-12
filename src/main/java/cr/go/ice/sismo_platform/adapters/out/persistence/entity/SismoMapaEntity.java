package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Sismos_Mapas", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SismoMapaEntity {

    @EmbeddedId
    private SismoId id;

    @MapsId
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "Fe_Sismo", referencedColumnName = "Fe_Sismo"),
            @JoinColumn(name = "Magnitud", referencedColumnName = "Magnitud")
    })
    private SismoEntity sismo;

    @Lob
    @Column(name = "mapa")
    private byte[] mapa;
}
