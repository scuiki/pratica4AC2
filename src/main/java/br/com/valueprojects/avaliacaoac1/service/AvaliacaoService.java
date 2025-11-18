package br.com.valueprojects.avaliacaoac1.service;

import br.com.valueprojects.avaliacaoac1.dto.AvaliacaoDTO;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.*;
import br.com.valueprojects.avaliacaoac1.gamificacao.service.GamificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final GamificacaoService gamificacaoService;

    public AvaliacaoDTO registrarAvaliacao(AvaliacaoDTO dto) {

        Aluno aluno = new Aluno(dto.getAlunoId(), true);
        Curso curso = new Curso(dto.getCursoCodigo());
        NotaAvaliacao nota = new NotaAvaliacao(dto.getNota());

        Avaliacao avaliacao = new Avaliacao(aluno, curso, nota);

        boolean aceita = gamificacaoService.concluirCurso(avaliacao);

        if (!aceita) {
            throw new IllegalArgumentException("Avaliação não aceita pelas regras de negócio (ex: média insuficiente).");
        }

        avaliacaoRepository.salvar(avaliacao);

        return AvaliacaoDTO.fromDomain(avaliacao);
    }
}
