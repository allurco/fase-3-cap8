package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Fontes;

import java.time.LocalDate;

public record FontesExibicaoDto(
        Long id,
        String nome,
        String tipo,
        String localizacao,
        LocalDate dataCriacao
) {

    public FontesExibicaoDto(Fontes fontes) {
        this(
                fontes.getId(),
                fontes.getNome(),
                fontes.getTipo(),
                fontes.getLocalizacao(),
                fontes.getDataCriacao()
        );
    }
}
