package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotaAvaliacaoTest {

    @Test
    void deveCriarNotaValida() {
        NotaAvaliacao nota = new NotaAvaliacao(8.5);

        assertEquals(8.5, nota.getValor());
    }

    @Test
    void deveRejeitarNotasMenoresQueZero() {
        assertThrows(IllegalArgumentException.class, () -> new NotaAvaliacao(-0.1));
    }

    @Test
    void deveRejeitarNotasMaioresQueDez() {
        assertThrows(IllegalArgumentException.class, () -> new NotaAvaliacao(10.1));
    }
}