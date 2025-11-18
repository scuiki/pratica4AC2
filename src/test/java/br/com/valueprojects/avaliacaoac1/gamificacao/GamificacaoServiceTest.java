package br.com.valueprojects.avaliacaoac1.gamificacao;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Aluno;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Avaliacao;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.AvaliacaoRepository;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Curso;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.NotaAvaliacao;
import br.com.valueprojects.avaliacaoac1.gamificacao.service.GamificacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GamificacaoServiceTest {

    private GamificacaoService novaInstanciaService() {
        AvaliacaoRepository repoMock = Mockito.mock(AvaliacaoRepository.class);

        when(repoMock.salvar(any(Avaliacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        return new GamificacaoService(repoMock);
    }

    @Test
    @DisplayName("Cenário 1 – Conclusão com média suficiente concede 3 créditos e salva avaliação")
    void cenario1_mediaSuficienteConcede3Creditos() {
        Aluno aluno = new Aluno(1, true);
        Curso curso = new Curso("CURSO-X");
        AvaliacaoRepository repoMock = Mockito.mock(AvaliacaoRepository.class);
        when(repoMock.salvar(any(Avaliacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        GamificacaoService svc = new GamificacaoService(repoMock);

        boolean concedeu = svc.concluirCurso(aluno, curso, 7.0);

        assertTrue(concedeu);
        assertEquals(3, aluno.getCreditos());

        verify(repoMock, times(1)).salvar(any(Avaliacao.class));
    }

    @Test
    @DisplayName("Cenário 2 – Conclusão com média insuficiente não concede créditos nem salva avaliação")
    void cenario2_mediaInsuficienteNaoConcede() {
        Aluno aluno = new Aluno(2, true);
        Curso curso = new Curso("CURSO-X");
        AvaliacaoRepository repoMock = Mockito.mock(AvaliacaoRepository.class);
        GamificacaoService svc = new GamificacaoService(repoMock);

        boolean concedeu = svc.concluirCurso(aluno, curso, 6.99);

        assertFalse(concedeu);
        assertEquals(0, aluno.getCreditos());

        verify(repoMock, never()).salvar(any(Avaliacao.class));
    }

    @Test
    @DisplayName("Cenário 3 – Repetição do mesmo curso não duplica recompensa nem salva segunda avaliação")
    void cenario3_repeticaoNaoDuplica() {
        Aluno aluno = new Aluno(3, true);
        Curso curso = new Curso("CURSO-X");
        AvaliacaoRepository repoMock = Mockito.mock(AvaliacaoRepository.class);
        when(repoMock.salvar(any(Avaliacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        GamificacaoService svc = new GamificacaoService(repoMock);

        assertTrue(svc.concluirCurso(aluno, curso, 8.0));
        assertFalse(svc.concluirCurso(aluno, curso, 9.0));

        assertEquals(3, aluno.getCreditos());

        verify(repoMock, times(1)).salvar(any(Avaliacao.class));
    }

}
