package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.CentroProduccionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CentroProduccionJpaRepository extends JpaRepository<CentroProduccionEntity, String> {
    @Query("""
            SELECT c
            FROM CentroProduccionEntity c
            WHERE (:codigo IS NULL OR :codigo = '' OR UPPER(c.codCentroPrd) LIKE UPPER(CONCAT(:codigo, '%')))
              AND (:nombre IS NULL OR :nombre = '' OR LOWER(c.nomCentroPrd) LIKE LOWER(CONCAT('%', :nombre, '%')))
            ORDER BY c.codCentroPrd
            """)
    Page<CentroProduccionEntity> search(
            @Param("codigo") String codigo,
            @Param("nombre") String nombre,
            Pageable pageable
    );
}
