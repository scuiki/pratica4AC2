package br.com.valueprojects.avaliacaoac1.repository;

import br.com.valueprojects.avaliacaoac1.gamificacao.dominio.NotaAvaliacao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int alunoId;
    private String cursoCodigo;

    @Embedded
    private NotaAvaliacao notaFinal;
}
