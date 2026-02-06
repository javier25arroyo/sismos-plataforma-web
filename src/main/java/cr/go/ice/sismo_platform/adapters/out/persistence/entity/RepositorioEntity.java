package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Repositorio", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositorioEntity {

    @Id
    @Column(name = "fe_Reporte", nullable = false)
    private LocalDateTime feReporte;

    @Column(name = "TituloReporte", length = 255)
    private String tituloReporte;

    @Column(name = "Resumen", length = 4000)
    private String resumen;

    @Column(name = "URL", length = 500)
    private String url;

    @Column(name = "Autor", length = 255)
    private String autor;

    @Lob
    @Column(name = "Archivo")
    private byte[] archivo;
}
