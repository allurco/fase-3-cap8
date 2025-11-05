package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(

        Long id,

        @NotBlank(message = "O campo nome não pode ficar vazio")
        String nome,

        @NotBlank(message = "O campo e-mail não pode ficar vazio")
        @Email(message = "O e-mail do usuário não é válido")
        String email,

        @NotBlank(message = "O campo senha não pode ficar vazio")
        @Size(min = 8, max = 32, message = "A senha deve ter entre 8 e 32 caracteres")
        String senha,



        UsuarioRole role
) {
}
