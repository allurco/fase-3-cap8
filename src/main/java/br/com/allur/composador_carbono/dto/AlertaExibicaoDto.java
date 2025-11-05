package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Alertas;
import java.time.LocalDate;

public record AlertaExibicaoDto(
    Long id,
    String tipo,
    String mensagem,
    LocalDate criadoEm,
    char resolvido
) {
    public AlertaExibicaoDto(Alertas alerta) {
        this(
            alerta.getId(),
            alerta.getTipo(),
            alerta.getMensagem(),
            alerta.getCriadoEm(),
            alerta.getResolvido()
        );
    }
}
