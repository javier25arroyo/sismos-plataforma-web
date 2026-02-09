package cr.go.ice.sismo_platform.domain.model;

public record Umbral(
        Integer codParametro,
        String codUmbral,
        Double limiteInferior,
        String mensaje,
        String color
) {}
