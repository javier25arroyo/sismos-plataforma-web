package cr.go.ice.sismo_platform.adapters.out.persistence.repository;

import cr.go.ice.sismo_platform.adapters.out.persistence.entity.UmbralEntity;
import cr.go.ice.sismo_platform.adapters.out.persistence.entity.UmbralId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UmbralJpaRepository extends JpaRepository<UmbralEntity, UmbralId> {
    List<UmbralEntity> findByCodParametro(Integer codParametro);
}
