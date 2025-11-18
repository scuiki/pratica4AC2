package br.com.valueprojects.avaliacaoac1;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class AvaliacaoAc1ApplicationMainTest {

    @Test
    void mainDeveChamarSpringApplicationRun() {
        try (MockedStatic<SpringApplication> springAppMock = Mockito.mockStatic(SpringApplication.class)) {

            springAppMock
                .when(() -> SpringApplication.run(eq(AvaliacaoAc1Application.class), any(String[].class)))
                .thenReturn(null);

            AvaliacaoAc1Application.main(new String[]{});

            springAppMock.verify(
                () -> SpringApplication.run(eq(AvaliacaoAc1Application.class), any(String[].class))
            );
        }
    }
}