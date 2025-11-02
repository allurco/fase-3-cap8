package br.com.allur.composador_carbono.dto;

import jakarta.validation.constraints.NotBlank;

public record FontesCadastroDto(

        @NotBlank(message = "Campo nome obrigatório!")
        String nome,
        String tipo,
        String localizacao
) {
}
