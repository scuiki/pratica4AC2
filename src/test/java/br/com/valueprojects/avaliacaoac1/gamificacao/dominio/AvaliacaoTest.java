package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoTest {

    @Test
    void deveCriarAvaliacaoValida() {
        Aluno aluno = new Aluno(1, true);
        Curso curso = new Curso("DEVOPS");
        NotaAvaliacao nota = new NotaAvaliacao(9.0);

        Avaliacao avaliacao = new Avaliacao(aluno, curso, nota);

        assertEquals(aluno, avaliacao.getAluno());
        assertEquals(curso, avaliacao.getCurso());
        assertEquals(nota, avaliacao.getNotaFinal());
    }

    @Test
    void deveLancarExcecaoQuandoAlunoForNulo() {
        Curso curso = new Curso("DEVOPS");
        NotaAvaliacao nota = new NotaAvaliacao(9.0);

        assertThrows(IllegalArgumentException.class,
                () -> new Avaliacao(null, curso, nota));
    }

    @Test
    void deveLancarExcecaoQuandoCursoForNulo() {
        Aluno aluno = new Aluno(1, true);
        NotaAvaliacao nota = new NotaAvaliacao(9.0);

        assertThrows(IllegalArgumentException.class,
                () -> new Avaliacao(aluno, null, nota));
    }

    @Test
    void deveLancarExcecaoQuandoNotaForNula() {
        Aluno aluno = new Aluno(1, true);
        Curso curso = new Curso("DEVOPS");

        assertThrows(IllegalArgumentException.class,
                () -> new Avaliacao(aluno, curso, null));
    }
}