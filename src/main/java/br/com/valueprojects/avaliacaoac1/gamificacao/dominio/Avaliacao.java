package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Avaliacao {

    private final Aluno aluno;
    private final Curso curso;
    private final NotaAvaliacao notaFinal;

    public Avaliacao(Aluno aluno, Curso curso, NotaAvaliacao notaFinal) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo na avaliação");
        }
        if (curso == null) {
            throw new IllegalArgumentException("Curso não pode ser nulo na avaliação");
        }
        if (notaFinal == null) {
            throw new IllegalArgumentException("Nota final não pode ser nula na avaliação");
        }
        this.aluno = aluno;
        this.curso = curso;
        this.notaFinal = notaFinal;
    }
}
