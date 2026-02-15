package cr.go.ice.sismo_platform.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(name = "PageResponse", description = "Respuesta paginada estable para endpoints REST")
public record PageResponse<T>(
        @Schema(description = "Elementos de la página actual")
        List<T> content,
        @Schema(description = "Índice de página actual (base 0)", example = "0")
        int page,
        @Schema(description = "Tamaño solicitado por página", example = "10")
        int size,
        @Schema(description = "Cantidad total de elementos", example = "42")
        long totalElements,
        @Schema(description = "Cantidad total de páginas", example = "5")
        int totalPages,
        @Schema(description = "Indica si la página actual es la primera", example = "true")
        boolean first,
        @Schema(description = "Indica si la página actual es la última", example = "false")
        boolean last,
        @Schema(description = "Cantidad de elementos en la página actual", example = "10")
        int numberOfElements,
        @Schema(description = "Indica si la página está vacía", example = "false")
        boolean empty
) {
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.isEmpty()
        );
    }
}
