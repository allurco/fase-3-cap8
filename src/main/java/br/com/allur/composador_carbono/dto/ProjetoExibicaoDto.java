package br.com.allur.composador_carbono.dto;

import br.com.allur.composador_carbono.models.Projetos;

public record ProjetoExibicaoDto(
    Long id,
    String nome,
    String tipo,
    String status
) {
    public ProjetoExibicaoDto(Projetos projeto) {
        this(projeto.getId(), projeto.getNome(), projeto.getTipo(), projeto.getStatus());
    }
}
