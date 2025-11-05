package br.com.allur.composador_carbono.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProjetosAtualizacaoDto(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 255, message = "O nome não pode exceder 255 caracteres.")
        String nome,

        @Size(max = 50, message = "O tipo não pode exceder 50 caracteres.")
        String tipo,

        @PositiveOrZero(message = "A capacidade em kg de CO2e deve ser um valor positivo ou zero.")
        Double capacidadeKgCo2e,

        @Size(max = 200, message = "A localização não pode exceder 200 caracteres.")
        String localizacao,

        @Pattern(regexp = "ATIVO|PAUSADO|FINALIZADO", message = "O status deve ser ATIVO, PAUSADO ou FINALIZADO.")
        String status
) {
}
