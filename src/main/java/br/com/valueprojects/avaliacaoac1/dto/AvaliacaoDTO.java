package br.com.valueprojects.avaliacaoac1.dto;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.Avaliacao;

public class AvaliacaoDTO {

    private int alunoId;
    private String cursoCodigo;
    private double nota;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(int alunoId, String cursoCodigo, double nota) {
        this.alunoId = alunoId;
        this.cursoCodigo = cursoCodigo;
        this.nota = nota;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public String getCursoCodigo() {
        return cursoCodigo;
    }

    public void setCursoCodigo(String cursoCodigo) {
        this.cursoCodigo = cursoCodigo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public static AvaliacaoDTO fromDomain(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setAlunoId(avaliacao.getAluno().getId());
        dto.setCursoCodigo(avaliacao.getCurso().getCodigo());
        dto.setNota(avaliacao.getNotaFinal().getValor());
        return dto;
    }
}
