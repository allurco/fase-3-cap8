package br.com.allur.composador_carbono.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmissoesCadastroDto(

        @NotNull(message = "A quantidade de CO2e é obrigatória.")
        @Positive(message = "A quantidade de CO2e deve ser um valor positivo.")
        BigDecimal quantidadeKgCo2e,

        @NotNull(message = "A categoria é obrigatória.")
        @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres.")
        String categoria,

        @NotNull(message = "A data e hora da emissão são obrigatórias.")
        LocalDateTime dataHora,

        Long idFonte, // FK para a tabela Fontes (não precisa de @NotNull se a regra permitir nulo)

        String metadados
) {}