## User Story (US)

| EU COMO        | PRECISO / QUERO                                                           | PARA                              |
|----------------|---------------------------------------------------------------------------|-----------------------------------|
| aluno assinante | receber **créditos adicionais** ao concluir um curso com média acima de 7 | poder continuar com o aprendizado |

---

## Cenários (BDD)

### Cenário 1 — Conclusão com média suficiente concede 3 créditos

| Dado que                        | E                           | E                                          | Quando                                  | Então                                                        |
|---------------------------------|-----------------------------|--------------------------------------------|-----------------------------------------|--------------------------------------------------------------|
| o aluno possui assinatura ativa | está matriculado no curso X | sua média final no curso é **7,0 ou maior** | o sistema registra a conclusão do curso | o saldo de **créditos_de_curso** do aluno **aumenta em 3** |

---

### Cenário 2 — Conclusão com média insuficiente

| Dado que                        | E                           | E                                            | Quando                                  | Então                                                     |
|---------------------------------|-----------------------------|----------------------------------------------|-----------------------------------------|-----------------------------------------------------------|
| o aluno possui assinatura ativa | está matriculado no curso X | sua média final no curso é **menor do que 7** | o sistema registra a conclusão do curso | o saldo de **créditos_de_curso** **permanece inalterado** |

---

### Cenário 3 — Repetição do mesmo curso não duplica recompensa

| Dado que                        | E                                              | Quando                      | E                                                   | Então                          |
|---------------------------------|------------------------------------------------|-----------------------------|-----------------------------------------------------|---------------------------------|
| o aluno possui assinatura ativa | **já concluiu o curso X com média acima de 7** | o aluno **refaz** o curso X | conclui novamente com média **igual ou acima de 7** | **nenhum crédito é concedido** |
