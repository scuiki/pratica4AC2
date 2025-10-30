package br.com.valueprojects.avaliacaoac1.gamificacao;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Aluno;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Curso;
import br.com.valueprojects.avaliacaoac1.gamificacao.service.GamificacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamificacaoServiceTest {
	@Test
    @DisplayName("Cenário 1 – Conclusão com média suficiente concede 3 créditos")
    void cenario1_mediaSuficienteConcede3Creditos() {
        Aluno aluno = new Aluno(1, true);
        Curso curso = new Curso("CURSO-X");
        GamificacaoService svc = new GamificacaoService();

        boolean concedeu = svc.concluirCurso(aluno, curso, 7.0);

        assertTrue(concedeu);
        assertEquals(3, aluno.getCreditos());
    }

    @Test
    @DisplayName("Cenário 2 – Conclusão com média insuficiente")
    void cenario2_mediaInsuficienteNaoConcede() {
        Aluno aluno = new Aluno(2, true);
        Curso curso = new Curso("CURSO-X");
        GamificacaoService svc = new GamificacaoService();

        boolean concedeu = svc.concluirCurso(aluno, curso, 6.99);

        assertFalse(concedeu);
        assertEquals(0, aluno.getCreditos());
    }

    @Test
    @DisplayName("Cenário 3 – Repetição do mesmo curso não duplica recompensa")
    void cenario3_repeticaoNaoDuplica() {
        Aluno aluno = new Aluno(3, true);
        Curso curso = new Curso("CURSO-X");
        GamificacaoService svc = new GamificacaoService();

        assertTrue(svc.concluirCurso(aluno, curso, 8.0));
        assertFalse(svc.concluirCurso(aluno, curso, 9.0)); 

        assertEquals(3, aluno.getCreditos());
    }

}
