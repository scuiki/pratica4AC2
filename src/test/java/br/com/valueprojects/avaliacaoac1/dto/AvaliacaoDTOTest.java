package br.com.valueprojects.avaliacaoac1.dto;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Aluno;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Avaliacao;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Curso;
import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.NotaAvaliacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvaliacaoDTOTest {

    @Test
    void construtorGettersESettersDevemFuncionar() {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setAlunoId(10);
        dto.setCursoCodigo("CURSO-10");
        dto.setNota(8.5);

        assertEquals(10, dto.getAlunoId());
        assertEquals("CURSO-10", dto.getCursoCodigo());
        assertEquals(8.5, dto.getNota());

        AvaliacaoDTO dto2 = new AvaliacaoDTO(1, "CURSO-1", 9.0);
        assertEquals(1, dto2.getAlunoId());
        assertEquals("CURSO-1", dto2.getCursoCodigo());
        assertEquals(9.0, dto2.getNota());
    }

    @Test
    void fromDomainDeveMapearTodosOsCampos() {
        Aluno aluno = new Aluno(1, true);
        Curso curso = new Curso("CURSO-1");
        NotaAvaliacao nota = new NotaAvaliacao(9.0);
        Avaliacao avaliacao = new Avaliacao(aluno, curso, nota);

        AvaliacaoDTO dto = AvaliacaoDTO.fromDomain(avaliacao);

        assertEquals(1, dto.getAlunoId());
        assertEquals("CURSO-1", dto.getCursoCodigo());
        assertEquals(9.0, dto.getNota());
    }
}