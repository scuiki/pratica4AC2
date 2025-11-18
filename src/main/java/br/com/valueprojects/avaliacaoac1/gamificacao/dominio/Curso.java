package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {

    @EqualsAndHashCode.Include
    private final String codigo;

    public Curso(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do curso não pode ser vazio");
        }
        this.codigo = codigo;
    }
}
