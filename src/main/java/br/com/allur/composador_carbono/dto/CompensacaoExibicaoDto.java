package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Compensacoes;
import java.time.LocalDate;

public record CompensacaoExibicaoDto(
    Long id,
    Long idEmissao,
    ProjetoExibicaoDto projeto,
    Double quantidadeCompensada,
    LocalDate data
) {
    public CompensacaoExibicaoDto(Compensacoes compensacao) {
        this(
            compensacao.getId(),
            compensacao.getEmissao().getId(),
            new ProjetoExibicaoDto(compensacao.getProjeto()),
            compensacao.getQuantidade(),
            compensacao.getDataHora()
        );
    }
}
