package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Emissoes;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmissoesExibicaoDto(
        Long id,
        FontesExibicaoDto fonte,
        Double quantidadeKgCo2e,
        String categoria,
        LocalDate dataHora,
        String metadados
) {
    public EmissoesExibicaoDto(Emissoes emissao) {
        this(
                emissao.getId(),
                emissao.getFontes() != null ? new FontesExibicaoDto(emissao.getFontes()) : null,
                emissao.getQuantidade(),
                emissao.getCategoria(),
                emissao.getDataHora(),
                emissao.getMetadados()
        );
    }
}