package br.com.valueprojects.avaliacaoac1.gamificacao.dominio;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotaAvaliacao {
   
    private double valor;

    public NotaAvaliacao(double valor) {
        if (valor < 0.0 || valor > 10.0) {
            throw new IllegalArgumentException("Nota de avaliação inválida: " + valor);
        }
        this.valor = valor;
    }
}
