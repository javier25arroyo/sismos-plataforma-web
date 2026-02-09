package cr.go.ice.sismo_platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI para la documentación de la API REST.
 * Proporciona la metadata base para Swagger UI.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sismoPlatformOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sismo Platform API")
                        .description("Plataforma de monitoreo y visualización de datos acelerográficos del ICE.")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Instituto Costarricense de Electricidad")
                                .url("https://www.grupointel.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
