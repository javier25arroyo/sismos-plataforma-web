package cr.go.ice.sismo_platform.adapters.in.web.dto;

public record UmbralResponse(
        String codParametro,
        String codUmbral,
        Double valor,
        String mensaje,
        String color
) {}
