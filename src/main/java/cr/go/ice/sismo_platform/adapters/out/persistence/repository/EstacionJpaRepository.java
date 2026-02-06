package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.EstacionEntity;
import cr.go.ice.sismo_platform.adapters.out.persistence.entity.EstacionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionJpaRepository extends JpaRepository<EstacionEntity, EstacionId> {
    Page<EstacionEntity> findByCodCentroPrd(String codCentroPrd, Pageable pageable);
    Page<EstacionEntity> findByCodCentroPrdAndNomEstacionContainingIgnoreCase(String codCentroPrd, String nombre, Pageable pageable);
}
