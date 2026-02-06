package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.SismoEntity;
import cr.go.ice.sismo_platform.adapters.out.persistence.entity.SismoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SismoResumenJpaRepository extends JpaRepository<SismoEntity, SismoId> {

    @Query(value = """
            SELECT
                Cod_Centro_Prd AS codCentroPrd,
                Nom_Centro_Prd AS nomCentroPrd,
                Cod_Estacion AS codEstacion,
                Nom_Estacion AS nomEstacion,
                Fe_dato AS feDato,
                Va_Aceleracion AS vaAceleracion,
                Va_Desplazamiento AS vaDesplazamiento,
                Fe_Sismo AS feSismo,
                Magnitud AS magnitud,
                Profundidad AS profundidad,
                Ubicacion AS ubicacion,
                CoordenadaX AS coordenadaX,
                CoordenadaY AS coordenadaY
            FROM dbo.vi_resumensismo
            WHERE Fe_Sismo = (SELECT MAX(Fe_Sismo) FROM dbo.Sismos)
              AND (:codCentroPrd IS NULL OR :codCentroPrd = '' OR Cod_Centro_Prd = :codCentroPrd)
              AND (:codEstacion IS NULL OR :codEstacion = '' OR Cod_Estacion = :codEstacion)
              AND (:desde IS NULL OR Fe_Sismo >= :desde)
              AND (:hasta IS NULL OR Fe_Sismo <= :hasta)
              AND (:magnitudMin IS NULL OR Magnitud >= :magnitudMin)
              AND (:magnitudMax IS NULL OR Magnitud <= :magnitudMax)
            ORDER BY Cod_Centro_Prd, Cod_Estacion
            """,
            countQuery = """
            SELECT COUNT(1)
            FROM dbo.vi_resumensismo
            WHERE Fe_Sismo = (SELECT MAX(Fe_Sismo) FROM dbo.Sismos)
              AND (:codCentroPrd IS NULL OR :codCentroPrd = '' OR Cod_Centro_Prd = :codCentroPrd)
              AND (:codEstacion IS NULL OR :codEstacion = '' OR Cod_Estacion = :codEstacion)
              AND (:desde IS NULL OR Fe_Sismo >= :desde)
              AND (:hasta IS NULL OR Fe_Sismo <= :hasta)
              AND (:magnitudMin IS NULL OR Magnitud >= :magnitudMin)
              AND (:magnitudMax IS NULL OR Magnitud <= :magnitudMax)
            """,
            nativeQuery = true)
    Page<SismoResumenProjection> findResumenUltimoSismo(
            @Param("codCentroPrd") String codCentroPrd,
            @Param("codEstacion") String codEstacion,
            @Param("desde") java.time.LocalDateTime desde,
            @Param("hasta") java.time.LocalDateTime hasta,
            @Param("magnitudMin") Double magnitudMin,
            @Param("magnitudMax") Double magnitudMax,
            Pageable pageable
    );
}
