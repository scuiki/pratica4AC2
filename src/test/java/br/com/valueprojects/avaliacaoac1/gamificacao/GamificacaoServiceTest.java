package br.com.valueprojects.avaliacaoac1.gamificacao;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Aluno;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Avaliacao;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.AvaliacaoRepository;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Curso;
import br.com.valueprojects.avaliacaoac1.gamificacao.service.GamificacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GamificacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private GamificacaoService gamificacaoService;

    @Test
    @DisplayName("Cenário 1 – Conclusão com média suficiente concede 3 créditos e salva avaliação")
    void cenario1_mediaSuficienteConcede3Creditos() {
        when(avaliacaoRepository.salvar(any(Avaliacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Aluno aluno = new Aluno(1, true);
        Curso curso = new Curso("CURSO-X");

        boolean concedeu = gamificacaoService.concluirCurso(aluno, curso, 7.0);

        assertTrue(concedeu);
        assertEquals(3, aluno.getCreditos());
        verify(avaliacaoRepository, times(1)).salvar(any(Avaliacao.class));
    }

    @Test
    @DisplayName("Cenário 2 – Conclusão com média insuficiente não concede créditos nem salva avaliação")
    void cenario2_mediaInsuficienteNaoConcede() {
        Aluno aluno = new Aluno(2, true);
        Curso curso = new Curso("CURSO-X");

        boolean concedeu = gamificacaoService.concluirCurso(aluno, curso, 6.99);

        assertFalse(concedeu);
        assertEquals(0, aluno.getCreditos());
        verify(avaliacaoRepository, never()).salvar(any(Avaliacao.class));
    }

    @Test
    @DisplayName("Cenário 3 – Repetição do mesmo curso não duplica recompensa nem salva segunda avaliação")
    void cenario3_repeticaoNaoDuplica() {
        when(avaliacaoRepository.salvar(any(Avaliacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Aluno aluno = new Aluno(3, true);
        Curso curso = new Curso("CURSO-X");

        assertTrue(gamificacaoService.concluirCurso(aluno, curso, 8.0));
        assertFalse(gamificacaoService.concluirCurso(aluno, curso, 9.0));

        assertEquals(3, aluno.getCreditos());
        verify(avaliacaoRepository, times(1)).salvar(any(Avaliacao.class));
    }
}