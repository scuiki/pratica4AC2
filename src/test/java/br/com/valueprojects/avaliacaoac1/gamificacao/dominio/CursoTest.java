package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    void deveCriarCursoComCodigoValido() {
        Curso curso = new Curso("ADS01");

        assertEquals("ADS01", curso.getCodigo());
    }

    @Test
    void deveLancarExcecaoQuandoCodigoEhVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Curso(""));
        assertThrows(IllegalArgumentException.class, () -> new Curso("   "));
        assertThrows(IllegalArgumentException.class, () -> new Curso(null));
    }

    @Test
    void cursosComMesmoCodigoDevemSerIguais() {
        Curso c1 = new Curso("ENGCOMP");
        Curso c2 = new Curso("ENGCOMP");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}