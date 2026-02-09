package cr.go.ice.sismo_platform.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuración de persistencia
 * Separa claramente la configuración de infraestructura
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "cr.go.ice.sismo_platform.adapters.out.persistence.repository"
)
@EntityScan(
    basePackages = "cr.go.ice.sismo_platform.adapters.out.persistence.entity"
)
@EnableTransactionManagement
public class PersistenceConfig {
    // Configuraciones adicionales de persistencia pueden ir aquí
}
