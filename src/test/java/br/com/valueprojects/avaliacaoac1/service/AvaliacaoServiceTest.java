package br.com.valueprojects.avaliacaoac1.service;

import br.com.valueprojects.avaliacaoac1.dto.AvaliacaoDTO;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Avaliacao;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.AvaliacaoRepository;
import br.com.valueprojects.avaliacaoac1.gamificacao.service.GamificacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private GamificacaoService gamificacaoService;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Test
    void registrarAvaliacaoQuandoAceitaDeveSalvarERetornarDTO() {
        AvaliacaoDTO entrada = new AvaliacaoDTO(1, "CURSO-1", 8.0);

        when(gamificacaoService.concluirCurso(any(Avaliacao.class))).thenReturn(true);

        when(avaliacaoRepository.salvar(any(Avaliacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AvaliacaoDTO resposta = avaliacaoService.registrarAvaliacao(entrada);

        ArgumentCaptor<Avaliacao> captor = ArgumentCaptor.forClass(Avaliacao.class);
        verify(avaliacaoRepository, times(1)).salvar(captor.capture());

        Avaliacao salvo = captor.getValue();
        assertEquals(1, salvo.getAluno().getId());
        assertEquals("CURSO-1", salvo.getCurso().getCodigo());
        assertEquals(8.0, salvo.getNotaFinal().getValor());

        assertEquals(1, resposta.getAlunoId());
        assertEquals("CURSO-1", resposta.getCursoCodigo());
        assertEquals(8.0, resposta.getNota());
    }

    @Test
    void registrarAvaliacaoQuandoRejeitadaDeveLancarExcecaoENaoSalvar() {
        AvaliacaoDTO entrada = new AvaliacaoDTO(2, "CURSO-2", 5.0);

        when(gamificacaoService.concluirCurso(any(Avaliacao.class))).thenReturn(false);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> avaliacaoService.registrarAvaliacao(entrada)
        );
        assertTrue(ex.getMessage().contains("Avaliação não aceita"));

        verify(avaliacaoRepository, never()).salvar(any(Avaliacao.class));
    }
}