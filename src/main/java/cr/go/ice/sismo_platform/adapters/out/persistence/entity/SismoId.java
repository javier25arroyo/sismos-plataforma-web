package cr.go.ice.sismo_platform.adapters.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SismoId implements Serializable {
    @Column(name = "Fe_Sismo", nullable = false)
    private LocalDateTime feSismo;

    @Column(name = "Magnitud", nullable = false)
    private Double magnitud;
}
