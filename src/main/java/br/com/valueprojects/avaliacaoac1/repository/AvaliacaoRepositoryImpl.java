package br.com.valueprojects.avaliacaoac1.repository;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    private final AvaliacaoJpaRepository jpaRepository;

    @Override
    public Avaliacao salvar(Avaliacao avaliacao) {
        AvaliacaoEntity entity = toEntity(avaliacao);
        AvaliacaoEntity salvo = jpaRepository.save(entity);
        return toDomain(salvo, avaliacao.getAluno(), avaliacao.getCurso());
    }

    private AvaliacaoEntity toEntity(Avaliacao avaliacao) {
        return AvaliacaoEntity.builder()
                .alunoId(avaliacao.getAluno().getId())
                .cursoCodigo(avaliacao.getCurso().getCodigo())
                .notaFinal(avaliacao.getNotaFinal())
                .build();
    }

    private Avaliacao toDomain(AvaliacaoEntity entity, Aluno aluno, Curso curso) {
        NotaAvaliacao nota = entity.getNotaFinal();
        return new Avaliacao(aluno, curso, nota);
    }
}