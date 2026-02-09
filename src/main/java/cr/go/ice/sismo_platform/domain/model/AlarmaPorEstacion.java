package cr.go.ice.sismo_platform.domain.model;

public record AlarmaPorEstacion(
        String codCentroPrd,
        String codEstacion,
        Integer codParametro,
        String codUmbral,
        Double limiteInferior
) {}

