package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.application.port.in.ReporteQuery;
import cr.go.ice.sismo_platform.config.SecurityConfig;
import cr.go.ice.sismo_platform.domain.model.ReporteRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReporteController.class)
@Import(SecurityConfig.class)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteQuery reporteQuery;

    @Test
    void listaReportesConFiltrosYpaginacion() throws Exception {
        LocalDateTime desde = LocalDateTime.parse("2025-01-01T00:00:00");
        LocalDateTime hasta = LocalDateTime.parse("2025-12-31T23:59:59");

        ReporteRepositorio reporte = new ReporteRepositorio(
                LocalDateTime.parse("2025-02-01T10:30:00"),
                "SISMO 2025",
                "Resumen",
                "/reportes/sismo-2025.pdf",
                "ICE",
                new byte[0]
        );
        Page<ReporteRepositorio> page = new PageImpl<>(List.of(reporte), PageRequest.of(0, 2), 1);

        when(reporteQuery.listarReportes(eq("SISMO"), eq("ICE"), eq(desde), eq(hasta), any()))
                .thenReturn(page);

        mockMvc.perform(get("/api/reportes")
                        .param("titulo", "SISMO")
                        .param("autor", "ICE")
                        .param("desde", "2025-01-01T00:00:00")
                        .param("hasta", "2025-12-31T23:59:59")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].titulo").value("SISMO 2025"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
