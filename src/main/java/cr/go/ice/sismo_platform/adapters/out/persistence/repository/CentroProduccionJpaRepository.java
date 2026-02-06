package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.CentroProduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CentroProduccionJpaRepository extends JpaRepository<CentroProduccionEntity, String> {
    Page<CentroProduccionEntity> findByNomCentroPrdContainingIgnoreCase(String nombre, Pageable pageable);
}
