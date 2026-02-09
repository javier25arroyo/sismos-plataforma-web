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
public class Usuario {
    String cedula;
    String nombreCompl;
    String correoE;
    String telOficina;
    String numCelular;
    Boolean estadoActivo;
    String usuarioNombre;
    Boolean notificarSms;
    LocalDateTime ultimoAcceso;
}
