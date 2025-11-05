package br.com.allur.composador_carbono.service;


import br.com.allur.composador_carbono.dto.ProjetosAtualizacaoDto;
import br.com.allur.composador_carbono.dto.ProjetosCadastroDto;
import br.com.allur.composador_carbono.dto.ProjetosExibicaoDto;
import br.com.allur.composador_carbono.exception.ProjetoNaoEncontradoException;
import br.com.allur.composador_carbono.models.Projetos;
import br.com.allur.composador_carbono.repository.ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjetosService {

    @Autowired
    private ProjetosRepository projetosRepository;

    public ProjetosExibicaoDto criar(ProjetosCadastroDto dto){
        Projetos projetos = new Projetos();
        projetos.setNome(dto.nome());
        projetos.setTipo(dto.tipo());
        projetos.setCapacidade(dto.capacidadeKgCo2e());
        projetos.setLocalizacao(dto.localizacao());

        // Definir status padrão se não for fornecido
        projetos.setStatus(dto.status() != null ? dto.status() : "ATIVO");

        // Definir anoReferencia como a data atual se não for fornecido no DTO
        // Como o DTO de cadastro não possui anoReferencia, sempre será LocalDate.now()
        projetos.setAnoReferencia(LocalDate.now());

        Projetos projetoSalvo = projetosRepository.save(projetos);
        return new ProjetosExibicaoDto(projetoSalvo);
    }

    public Page<ProjetosExibicaoDto> listar(Pageable paginacao) {
        return projetosRepository
                .findAll(paginacao)
                .map(ProjetosExibicaoDto::new);
    }

    public ProjetosExibicaoDto atualizar(Long id, ProjetosAtualizacaoDto dto) {
        Projetos projeto = projetosRepository.findById(id)
                .orElseThrow(() -> new ProjetoNaoEncontradoException("Projeto não encontrado com o id: " + id));

        // Atualização total
        projeto.setNome(dto.nome());
        projeto.setTipo(dto.tipo());
        projeto.setCapacidade(dto.capacidadeKgCo2e());
        projeto.setLocalizacao(dto.localizacao());
        projeto.setStatus(dto.status());
        // O ano de referência não é atualizado, pois representa quando o projeto foi criado.

        Projetos projetoAtualizado = projetosRepository.save(projeto);
        return new ProjetosExibicaoDto(projetoAtualizado);
    }

    public void excluir(Long id) {
        if (!projetosRepository.existsById(id)) {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado com o id: " + id);
        }
        projetosRepository.deleteById(id);
    }
}
