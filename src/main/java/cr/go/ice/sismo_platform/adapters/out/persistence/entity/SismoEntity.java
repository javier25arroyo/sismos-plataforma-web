package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Sismos", schema = "dbo")
@IdClass(SismoId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SismoEntity {

    @Id
    @Column(name = "Fe_Sismo", nullable = false)
    private LocalDateTime feSismo;

    @Id
    @Column(name = "Magnitud", nullable = false)
    private Double magnitud;

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
    @Column(name = "Mapa")
    private byte[] mapa;
}
