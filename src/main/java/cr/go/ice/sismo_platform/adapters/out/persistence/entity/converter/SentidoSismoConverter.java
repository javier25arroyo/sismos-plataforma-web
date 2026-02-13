package cr.go.ice.sismo_platform.adapters.out.persistence.entity.converter;

import cr.go.ice.sismo_platform.domain.model.SentidoSismo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Conversor JPA para mapear {@link SentidoSismo} <-> CHAR(1) (columna Sentido).
 * No altera el tipo de dato en base de datos.
 */
@Converter
public class SentidoSismoConverter implements AttributeConverter<SentidoSismo, String> {

    @Override
    public String convertToDatabaseColumn(SentidoSismo attribute) {
        return attribute == null ? null : attribute.databaseValue();
    }

    @Override
    public SentidoSismo convertToEntityAttribute(String dbData) {
        return dbData == null ? null : SentidoSismo.fromValue(dbData);
    }
}
