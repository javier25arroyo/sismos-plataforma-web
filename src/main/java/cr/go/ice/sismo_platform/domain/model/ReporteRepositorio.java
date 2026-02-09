package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;
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
public class ReporteRepositorio {
    LocalDateTime feReporte;
    String tituloReporte;
    String resumen;
    String url;
    String autor;
    byte[] archivo;
}
