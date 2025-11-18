package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void deveCriarAlunoComIdEAssinatura() {
        Aluno aluno = new Aluno(1, true);

        assertEquals(1, aluno.getId());
        assertTrue(aluno.isAssinaturaAtiva());
        assertEquals(0, aluno.getCreditos());
    }

    @Test
    void deveAdicionarCreditosAoAluno() {
        Aluno aluno = new Aluno(1, true);

        aluno.adicionarCreditos(10);

        assertEquals(10, aluno.getCreditos());
        aluno.adicionarCreditos(5);
        assertEquals(15, aluno.getCreditos());
    }

    @Test
    void alunosComMesmoIdDevemSerIguais() {
        Aluno a1 = new Aluno(1, true);
        Aluno a2 = new Aluno(1, false);

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }
}