package br.com.valueprojects.avaliacaoac1.gamificacao.service;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Aluno;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Curso;

import java.util.*;

public class GamificacaoService {
    private static final double MEDIA_MINIMA = 7.0;
    private static final int CREDITOS_RECOMPENSA = 3;

    private final Map<Integer, Set<String>> recompensas = new HashMap<>();

    public boolean concluirCurso(Aluno aluno, Curso curso, double mediaFinal) {
        if (entradaInvalida(aluno, curso)) return false;
        if (mediaInsuficiente(mediaFinal)) return false;
        if (jaRecompensado(aluno, curso)) return false;

        recompensas.computeIfAbsent(aluno.getId(), k -> new HashSet<>())
                   .add(curso.getCodigo());
        aluno.adicionarCreditos(CREDITOS_RECOMPENSA);
        return true;
    }

    private boolean entradaInvalida(Aluno a, Curso c) {
        return a == null || c == null || !a.isAssinaturaAtiva();
    }
    private boolean mediaInsuficiente(double m) {
        return m < MEDIA_MINIMA;
    }
    private boolean jaRecompensado(Aluno a, Curso c) {
        return recompensas.getOrDefault(a.getId(), Set.of())
                          .contains(c.getCodigo());
    }
}
