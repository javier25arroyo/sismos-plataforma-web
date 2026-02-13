package cr.go.ice.sismo_platform.adapters.out.persistence.entity.converter;

import cr.go.ice.sismo_platform.domain.model.NivelUmbral;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Conversor JPA para mapear {@link NivelUmbral} <-> VARCHAR(Cod_umbral).
 * No altera el tipo de dato en base de datos.
 */
@Converter
public class NivelUmbralConverter implements AttributeConverter<NivelUmbral, String> {

    @Override
    public String convertToDatabaseColumn(NivelUmbral attribute) {
        return attribute == null ? null : attribute.databaseValue();
    }

    @Override
    public NivelUmbral convertToEntityAttribute(String dbData) {
        return dbData == null ? null : NivelUmbral.fromValue(dbData);
    }
}
