package br.com.valueprojects.avaliacaoac1.controller;

import br.com.valueprojects.avaliacaoac1.dto.AvaliacaoDTO;
import br.com.valueprojects.avaliacaoac1.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Avaliações", description = "Endpoints relacionados ao registro de avaliações")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registrar uma avaliação",
            description = "Recebe uma avaliação com aluno, curso e nota; aplica regras de gamificação e salva a avaliação."
    )
    public AvaliacaoDTO registrarAvaliacao(@RequestBody AvaliacaoDTO dto) {
        return avaliacaoService.registrarAvaliacao(dto);
    }

}
