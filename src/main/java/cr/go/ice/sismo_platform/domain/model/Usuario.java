package cr.go.ice.sismo_platform.domain.model;

import java.time.LocalDateTime;

public record Usuario(
        String cedula,
        String nombreCompl,
        String correoE,
        String telOficina,
        String numCelular,
        Boolean estadoActivo,
        String usuarioNombre,
        Boolean notificarSms,
        LocalDateTime ultimoAcceso
) {}

