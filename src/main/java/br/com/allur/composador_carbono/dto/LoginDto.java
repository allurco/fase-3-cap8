package br.com.allur.composador_carbono.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(

        @NotBlank(message = "O e-mail do usuário é obrigatório!")
        @Email(message = "O e-mail está inválido")
        String email,


        @NotBlank(message = "A senha do usuário é obrigatória!")
        @Size(min = 8, max = 32, message = "A senha deve conter entre 8 e 32 caracteres")
        String senha
) {
}
