package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

public class Aluno {
    private final int id;
    private final boolean assinaturaAtiva;
    private int creditos;

    public Aluno(int id, boolean assinaturaAtiva) {
        this.id = id;
        this.assinaturaAtiva = assinaturaAtiva;
    }
    public int getId() { return id; }
    public boolean isAssinaturaAtiva() { return assinaturaAtiva; }
    public int getCreditos() { return creditos; }
    public void adicionarCreditos(int qtd) { this.creditos += qtd; }
}
