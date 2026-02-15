package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "app.security.enabled=false"
)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@Tag("integration")
class CentroEstacionIntegrationTest {

    @Container
    static MSSQLServerContainer<?> sqlServer = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest")
            .acceptLicense();

    @DynamicPropertySource
    static void datasourceProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlServer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlServer::getUsername);
        registry.add("spring.datasource.password", sqlServer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
        registry.add("spring.jpa.open-in-view", () -> "false");
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.SQLServerDialect");
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupSchema() {
        jdbcTemplate.execute("IF OBJECT_ID('dbo.Estaciones', 'U') IS NOT NULL DROP TABLE dbo.Estaciones");
        jdbcTemplate.execute("IF OBJECT_ID(N'dbo.Centro_Producción', N'U') IS NOT NULL DROP TABLE dbo.[Centro_Producción]");

        jdbcTemplate.execute("""
                CREATE TABLE dbo.[Centro_Producción] (
                    Cod_Centro_Prd VARCHAR(2) NOT NULL PRIMARY KEY,
                    Nom_Centro_Prd VARCHAR(30) NOT NULL,
                    CoordenadaX FLOAT NULL,
                    CoordenadaY FLOAT NULL,
                    Grupo_despliegue SMALLINT NULL
                )
                """);

        jdbcTemplate.execute("""
                CREATE TABLE dbo.Estaciones (
                    Cod_Centro_Prd VARCHAR(2) NOT NULL,
                    Cod_Estacion VARCHAR(2) NOT NULL,
                    Nom_Estacion VARCHAR(30) NOT NULL,
                    CoordenadaX FLOAT NULL,
                    CoordenadaY FLOAT NULL,
                    CONSTRAINT PK_Estaciones PRIMARY KEY (Cod_Centro_Prd, Cod_Estacion)
                )
                """);

        jdbcTemplate.update("INSERT INTO dbo.[Centro_Producción] (Cod_Centro_Prd, Nom_Centro_Prd) VALUES ('CB', 'C.P. CARIBLANCO')");
        jdbcTemplate.update("INSERT INTO dbo.[Centro_Producción] (Cod_Centro_Prd, Nom_Centro_Prd) VALUES ('AN', 'C.P. ANGOSTURA')");

        jdbcTemplate.update("INSERT INTO dbo.Estaciones (Cod_Centro_Prd, Cod_Estacion, Nom_Estacion, CoordenadaX, CoordenadaY) VALUES ('CB', 'BA', 'Base', -84.178, 10.309)");
        jdbcTemplate.update("INSERT INTO dbo.Estaciones (Cod_Centro_Prd, Cod_Estacion, Nom_Estacion, CoordenadaX, CoordenadaY) VALUES ('CB', 'CR', 'Cresta', -84.170, 10.320)");
        jdbcTemplate.update("INSERT INTO dbo.Estaciones (Cod_Centro_Prd, Cod_Estacion, Nom_Estacion, CoordenadaX, CoordenadaY) VALUES ('AN', 'CA', 'Caseta', -83.642, 9.871)");
    }

    @Test
    void listaCentrosConFiltroPorCodigo() throws Exception {
        mockMvc.perform(get("/api/centros")
                        .param("codigo", "C")
                        .param("size", "10")
                        .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].codCentroPrd").value("CB"));
    }

    @Test
    void listaEstacionesConRangoCoordenadas() throws Exception {
        mockMvc.perform(get("/api/centros/CB/estaciones")
                        .param("minX", "-84.179")
                        .param("maxX", "-84.175")
                        .param("minY", "10.300")
                        .param("maxY", "10.310")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].codEstacion").value("BA"));
    }
}
