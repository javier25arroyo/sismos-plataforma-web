package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.application.port.in.SismoResumenQuery;
import cr.go.ice.sismo_platform.config.SecurityConfig;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SismoController.class)
@Import(SecurityConfig.class)
class SismoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SismoResumenQuery sismoResumenQuery;

    @Test
    void resumenUltimoSismoConFiltrosYpaginacion() throws Exception {
        LocalDateTime desde = LocalDateTime.parse("2025-01-01T00:00:00");
        LocalDateTime hasta = LocalDateTime.parse("2025-12-31T23:59:59");

        SismoResumen resumen = new SismoResumen(
                "CB",
                "C.P. CARIBLANCO",
                "BA",
                "Base",
                LocalDateTime.parse("2025-02-01T10:00:00"),
                0.12,
                0.01,
                LocalDateTime.parse("2025-02-01T10:00:00"),
                4.5,
                10.0,
                "Ubicacion",
                -84.17,
                10.30
        );

        Page<SismoResumen> page = new PageImpl<>(List.of(resumen), PageRequest.of(0, 1), 1);

        when(sismoResumenQuery.obtenerResumenUltimoSismo(eq("CB"), eq("BA"), eq(desde), eq(hasta), eq(4.0), eq(6.0), any()))
                .thenReturn(page);

        mockMvc.perform(get("/api/sismos/ultimo/resumen")
                        .param("codCentroPrd", "CB")
                        .param("codEstacion", "BA")
                        .param("desde", "2025-01-01T00:00:00")
                        .param("hasta", "2025-12-31T23:59:59")
                        .param("magnitudMin", "4.0")
                        .param("magnitudMax", "6.0")
                        .param("page", "0")
                        .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].codCentroPrd").value("CB"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
