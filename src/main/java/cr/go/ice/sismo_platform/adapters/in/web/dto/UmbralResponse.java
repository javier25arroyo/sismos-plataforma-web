package cr.go.ice.sismo_platform.adapters.in.web.dto;

public record UmbralResponse(
        Integer codParametro,
        String codUmbral,
        Double limiteInferior,
        String mensaje,
        String color
) {

}
