package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.RepositorioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RepositorioJpaRepository extends JpaRepository<RepositorioEntity, LocalDateTime> {

    @Query("""
            SELECT r
            FROM RepositorioEntity r
            WHERE (:titulo IS NULL OR :titulo = '' OR LOWER(r.tituloReporte) LIKE LOWER(CONCAT('%', :titulo, '%')))
              AND (:autor IS NULL OR :autor = '' OR LOWER(r.autor) LIKE LOWER(CONCAT('%', :autor, '%')))
              AND (:desde IS NULL OR r.feReporte >= :desde)
              AND (:hasta IS NULL OR r.feReporte <= :hasta)
            ORDER BY r.feReporte DESC
            """)
    Page<RepositorioEntity> search(
            @Param("titulo") String titulo,
            @Param("autor") String autor,
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta,
            Pageable pageable
    );
}
