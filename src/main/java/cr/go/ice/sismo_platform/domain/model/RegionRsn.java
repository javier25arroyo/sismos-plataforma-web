package cr.go.ice.sismo_platform.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(fluent = true)
public class RegionRsn {
    Integer id;
    String nombre;
    Double coordenadaY;
    Double coordenadaX;
    Integer rango;
}
