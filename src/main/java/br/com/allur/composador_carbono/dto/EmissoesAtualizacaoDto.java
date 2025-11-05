package br.com.allur.composador_carbono.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmissoesAtualizacaoDto(
        @Positive(message = "A quantidade de CO2e deve ser um valor positivo.")
        BigDecimal quantidadeKgCo2e,

        @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres.")
        String categoria,

        LocalDateTime dataHora,

        Long idFonte,

        String metadados
) {}