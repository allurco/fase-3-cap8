package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.models.UsuarioRole;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
