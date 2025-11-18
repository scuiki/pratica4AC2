package br.com.valueprojects.avaliacaoac1.gamificacao.service;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GamificacaoService {

    private static final double MEDIA_MINIMA = 7.0;
    private static final int CREDITOS_RECOMPENSA = 3;

    private final AvaliacaoRepository avaliacaoRepository;

    private final Map<Integer, Set<String>> recompensas = new HashMap<>();

    public boolean concluirCurso(Avaliacao avaliacao) {
        if (entradaInvalida(avaliacao)) return false;
        if (mediaInsuficiente(avaliacao.getNotaFinal().getValor())) return false;
        if (jaRecompensado(avaliacao.getAluno(), avaliacao.getCurso())) return false;

        salvarRecompensa(avaliacao);
        avaliacao.getAluno().adicionarCreditos(CREDITOS_RECOMPENSA);

        avaliacaoRepository.salvar(avaliacao);

        return true;
    }

    public boolean concluirCurso(Aluno aluno, Curso curso, double mediaFinal) {
        Avaliacao avaliacao = new Avaliacao(aluno, curso, new NotaAvaliacao(mediaFinal));
        return concluirCurso(avaliacao);
    }

    private boolean entradaInvalida(Avaliacao avaliacao) {
        Aluno a = avaliacao.getAluno();
        Curso c = avaliacao.getCurso();
        return a == null || c == null || !a.isAssinaturaAtiva();
    }

    private boolean mediaInsuficiente(double m) {
        return m < MEDIA_MINIMA;
    }

    private boolean jaRecompensado(Aluno a, Curso c) {
        return recompensas.getOrDefault(a.getId(), Set.of()).contains(c.getCodigo());
    }

    private void salvarRecompensa(Avaliacao avaliacao) {
        recompensas
            .computeIfAbsent(avaliacao.getAluno().getId(), k -> new HashSet<>())
            .add(avaliacao.getCurso().getCodigo());
    }
}
