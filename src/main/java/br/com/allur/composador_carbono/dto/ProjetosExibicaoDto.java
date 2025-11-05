package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Projetos;

import java.time.LocalDate;

public record ProjetosExibicaoDto(
        Long id,
        String nome,
        String tipo,
        Double capacidadeKgCo2e,
        String localizacao,
        LocalDate anoReferencia,
        String status
) {
    public ProjetosExibicaoDto(Projetos projetos) {
        this(
                projetos.getId(),
                projetos.getNome(),
                projetos.getTipo(),
                projetos.getCapacidade(), // Mapeando capacidade -> capacidadeKgCo2e
                projetos.getLocalizacao(),
                projetos.getAnoReferencia(),
                projetos.getStatus()
        );
    }
}
