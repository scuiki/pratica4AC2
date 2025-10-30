package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import java.util.Objects;

public class Curso {
    private final String codigo;
    public Curso(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }
    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Curso)) return false;
        Curso c=(Curso)o;
        return Objects.equals(codigo, c.codigo);
    }
    @Override public int hashCode(){ return Objects.hash(codigo); }
}
