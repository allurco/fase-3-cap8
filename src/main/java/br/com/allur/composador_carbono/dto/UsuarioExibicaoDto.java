package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Usuario;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email
) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
