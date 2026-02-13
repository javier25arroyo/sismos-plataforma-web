package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.converter.SentidoSismoConverter;
import cr.go.ice.sismo_platform.domain.model.SentidoSismo;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Sismos", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SismoEntity {

    @EmbeddedId
    private SismoId id;

    @Column(name = "Profundidad", nullable = false)
    private Double profundidad;

    @Column(name = "Ubicacion", length = 150, nullable = false)
    private String ubicacion;

    @Column(name = "CoordenadaX", nullable = false)
    private Double coordenadaX;

    @Column(name = "CoordenadaY", nullable = false)
    private Double coordenadaY;

    @Column(name = "URL_Fuente", length = 250)
    private String urlFuente;

    @Lob
    @Column(name = "mapa")
    private byte[] mapa;

    @Column(name = "Sentido", length = 1)
    @Convert(converter = SentidoSismoConverter.class)
    private SentidoSismo sentido;

    @OneToOne(mappedBy = "sismo")
    private SismoMapaEntity sismoMapa;
}
