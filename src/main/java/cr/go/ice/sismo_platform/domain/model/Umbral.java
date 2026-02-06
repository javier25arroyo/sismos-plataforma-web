package cr.go.ice.sismo_platform.domain.model;

public record Umbral(
        String codParametro,
        String codUmbral,
        Double valor,
        String mensaje,
        String color
) {}
