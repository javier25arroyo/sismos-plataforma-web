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
@Table(name = "Umbrales", schema = "dbo")
@IdClass(UmbralId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UmbralEntity {

    @Id
    @Column(name = "Cod_Parametro", length = 3, nullable = false)
    private String codParametro;

    @Id
    @Column(name = "Cod_umbral", length = 10, nullable = false)
    private String codUmbral;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "Mensaje", length = 255)
    private String mensaje;

    @Column(name = "Color", length = 10)
    private String color;
}
