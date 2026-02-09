package cr.go.ice.sismo_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Configuración de la aplicación
 * Define beans de infraestructura que pueden ser necesarios en el dominio
 */
@Configuration
public class ApplicationConfig {

    /**
     * Bean para manejo del tiempo - útil para testing y consistencia
     */
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
