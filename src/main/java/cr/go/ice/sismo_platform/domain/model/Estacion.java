package cr.go.ice.sismo_platform.domain.model;

public record Estacion(
        String codCentroPrd,
        String codEstacion,
        String nomEstacion,
        Double coordenadaX,
        Double coordenadaY,
        String nuSerie,
        Short idCanal,
        String tipoInstrumento,
        Double eleEstacion,
        String tipoSuelo,
        String condiSitio,
        Double deltaT,
        Double azimut,
        String chn1Long,
        String chn2Vert,
        String chn3Trans,
        Double bitWeightCh1,
        Double bitWeightCh2,
        Double bitWeightCh3,
        Double vpuCh1,
        Double vpuCh2,
        Double vpuCh3,
        String modInst
) {}
