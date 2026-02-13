package cr.go.ice.sismo_platform.adapters.in.web;

import cr.go.ice.sismo_platform.application.port.in.SismoResumenQuery;
import cr.go.ice.sismo_platform.config.SecurityConfig;
import cr.go.ice.sismo_platform.domain.model.SismoResumen;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SismoController.class)
@Import(SecurityConfig.class)
@Tag("performance")
class SismoControllerPerformanceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SismoResumenQuery sismoResumenQuery;

    @Test
    void resumenUltimoSismoDebeMantenerLatenciaBaja() throws Exception {
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

        Page<SismoResumen> page = new PageImpl<>(List.of(resumen), PageRequest.of(0, 10), 1);
        when(sismoResumenQuery.obtenerResumenUltimoSismo(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(page);

        int warmupRequests = 25;
        int measuredRequests = 200;
        long[] durationsNs = new long[measuredRequests];

        for (int i = 0; i < warmupRequests; i++) {
            performRequest().andExpect(status().isOk());
        }

        for (int i = 0; i < measuredRequests; i++) {
            long start = System.nanoTime();
            performRequest().andExpect(status().isOk());
            durationsNs[i] = System.nanoTime() - start;
        }

        Arrays.sort(durationsNs);
        double avgMs = Arrays.stream(durationsNs).average().orElse(0.0) / 1_000_000.0;
        double p95Ms = durationsNs[(int) Math.ceil(durationsNs.length * 0.95) - 1] / 1_000_000.0;

        // Umbrales "smoke": detectan regresiones grandes sin volver flaky el test.
        assertThat(avgMs).isLessThan(120.0);
        assertThat(p95Ms).isLessThan(250.0);
    }

    private ResultActions performRequest() throws Exception {
        return mockMvc.perform(get("/api/sismos/ultimo/resumen")
                .param("codCentroPrd", "CB")
                .param("codEstacion", "BA")
                .param("desde", "2025-01-01T00:00:00")
                .param("hasta", "2025-12-31T23:59:59")
                .param("magnitudMin", "4.0")
                .param("magnitudMax", "6.0")
                .param("page", "0")
                .param("size", "10"));
    }
}
