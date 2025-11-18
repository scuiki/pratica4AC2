package br.com.valueprojects.avaliacaoac1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gamificação - Plataforma de Cursos",
                version = "v1",
                description = "API para regras de gamificação (conclusão de curso, créditos, etc.)"
        )
)
public class OpenApiConfig {

}
