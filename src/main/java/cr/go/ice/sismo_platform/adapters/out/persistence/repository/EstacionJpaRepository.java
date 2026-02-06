package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.EstacionEntity;
import cr.go.ice.sismo_platform.adapters.out.persistence.entity.EstacionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstacionJpaRepository extends JpaRepository<EstacionEntity, EstacionId> {
    @Query("""
            SELECT e
            FROM EstacionEntity e
            WHERE e.codCentroPrd = :codCentroPrd
              AND (:codEstacion IS NULL OR :codEstacion = '' OR UPPER(e.codEstacion) = UPPER(:codEstacion))
              AND (:nombre IS NULL OR :nombre = '' OR LOWER(e.nomEstacion) LIKE LOWER(CONCAT('%', :nombre, '%')))
              AND (:minX IS NULL OR e.coordenadaX >= :minX)
              AND (:maxX IS NULL OR e.coordenadaX <= :maxX)
              AND (:minY IS NULL OR e.coordenadaY >= :minY)
              AND (:maxY IS NULL OR e.coordenadaY <= :maxY)
            ORDER BY e.codCentroPrd, e.codEstacion
            """)
    Page<EstacionEntity> search(
            @Param("codCentroPrd") String codCentroPrd,
            @Param("codEstacion") String codEstacion,
            @Param("nombre") String nombre,
            @Param("minX") Double minX,
            @Param("maxX") Double maxX,
            @Param("minY") Double minY,
            @Param("maxY") Double maxY,
            Pageable pageable
    );
}
