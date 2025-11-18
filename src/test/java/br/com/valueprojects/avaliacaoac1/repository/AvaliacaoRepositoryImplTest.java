package br.com.valueprojects.avaliacaoac1.repository;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Aluno;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Avaliacao;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Curso;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.NotaAvaliacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AvaliacaoRepositoryImpl.class)
class AvaliacaoRepositoryImplTest {

    @Autowired
    private AvaliacaoRepositoryImpl avaliacaoRepository;

    @Autowired
    private AvaliacaoJpaRepository jpaRepository;

    @Test
    void deveSalvarAvaliacaoNoBancoUsandoJpaRepository() {
        Aluno aluno = new Aluno(123, true);
        Curso curso = new Curso("DEVOPS");
        NotaAvaliacao nota = new NotaAvaliacao(8.0);
        Avaliacao avaliacao = new Avaliacao(aluno, curso, nota);

        Avaliacao salvo = avaliacaoRepository.salvar(avaliacao);

        assertNotNull(salvo);
        assertEquals(aluno, salvo.getAluno());
        assertEquals(curso, salvo.getCurso());
        assertEquals(nota, salvo.getNotaFinal());

        assertEquals(1, jpaRepository.count());
        AvaliacaoEntity entity = jpaRepository.findAll().get(0);
        assertEquals(123, entity.getAlunoId());
        assertEquals("DEVOPS", entity.getCursoCodigo());
        assertEquals(8.0, entity.getNotaFinal().getValor());
    }
}
