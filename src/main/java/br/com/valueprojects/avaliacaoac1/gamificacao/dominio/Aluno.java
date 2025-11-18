package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno {

    @EqualsAndHashCode.Include
    private final int id;

    private final boolean assinaturaAtiva;

    private int creditos;

    public Aluno(int id, boolean assinaturaAtiva) {
        this.id = id;
        this.assinaturaAtiva = assinaturaAtiva;
    }

    public void adicionarCreditos(int qtd) {
        this.creditos += qtd;
    }
}