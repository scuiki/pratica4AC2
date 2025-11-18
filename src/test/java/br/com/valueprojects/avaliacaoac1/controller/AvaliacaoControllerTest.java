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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AvaliacaoController.class)
class AvaliacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvaliacaoService avaliacaoService;

    @Test
    void registrarAvaliacaoEndpointDeveRetornarCreatedEJson() throws Exception {
        AvaliacaoDTO resposta = new AvaliacaoDTO(1, "CURSO-1", 8.0);
        when(avaliacaoService.registrarAvaliacao(any(AvaliacaoDTO.class))).thenReturn(resposta);

        String jsonRequest = """
                {
                  "alunoId": 1,
                  "cursoCodigo": "CURSO-1",
                  "nota": 8.0
                }
                """;

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.alunoId").value(1))
                .andExpect(jsonPath("$.cursoCodigo").value("CURSO-1"))
                .andExpect(jsonPath("$.nota").value(8.0));

        verify(avaliacaoService).registrarAvaliacao(any(AvaliacaoDTO.class));
    }
}