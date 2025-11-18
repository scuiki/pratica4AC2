package br.com.valueprojects.avaliacaoac1.controller;

import br.com.valueprojects.avaliacaoac1.dto.AvaliacaoDTO;
import br.com.valueprojects.avaliacaoac1.service.AvaliacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AvaliacaoController.class)
class AvaliacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvaliacaoService avaliacaoService;

    @Test
    void deveChamarServiceENotificarCreatedAoRegistrarAvaliacao() throws Exception {
        when(avaliacaoService.registrarAvaliacao(any(AvaliacaoDTO.class)))
                .thenReturn(null);

        String jsonBody = """
                {
                  "alunoId": 123,
                  "cursoCodigo": "DEVOPS",
                  "notaFinal": 9.0
                }
                """;

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());

        verify(avaliacaoService).registrarAvaliacao(any(AvaliacaoDTO.class));
    }
}