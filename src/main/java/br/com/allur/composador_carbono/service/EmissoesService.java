package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.dto.EmissoesAtualizacaoDto;
import br.com.allur.composador_carbono.dto.EmissoesCadastroDto;
import br.com.allur.composador_carbono.dto.EmissoesExibicaoDto;
import br.com.allur.composador_carbono.dto.EmissoesAtualizacaoDto;
import br.com.allur.composador_carbono.dto.EmissoesCadastroDto;
import br.com.allur.composador_carbono.dto.EmissoesExibicaoDto;
import br.com.allur.composador_carbono.models.Emissoes;
import br.com.allur.composador_carbono.models.Fontes;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.models.UsuarioRole;
import br.com.allur.composador_carbono.repository.EmissoesRepository;
import br.com.allur.composador_carbono.repository.FontesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmissoesService {

    @Autowired
    private EmissoesRepository emissoesRepository;

    @Autowired
    private FontesRepository fontesRepository;


    public EmissoesExibicaoDto criar(EmissoesCadastroDto emissoesCadastroDto, Usuario usuario) {
        Fontes fontes = fontesRepository.findById(emissoesCadastroDto.idFonte()).orElseThrow(() -> new EntityNotFoundException("Fonte não encontrada"));
        Emissoes emissoes = new Emissoes(emissoesCadastroDto, usuario);
        emissoes.setFontes(fontes);
        emissoesRepository.save(emissoes);
        return new EmissoesExibicaoDto(emissoes);
    }

    public Page<EmissoesExibicaoDto> listar(Pageable pageable, Usuario usuario) {
        if (usuario.getRole() == UsuarioRole.ADMIN) {
            return emissoesRepository.findAll(pageable).map(EmissoesExibicaoDto::new);
        } else {
            return emissoesRepository.findAllByUsuario(usuario, pageable).map(EmissoesExibicaoDto::new);
        }
    }

    @Transactional
    public EmissoesExibicaoDto atualizar(Long id, EmissoesAtualizacaoDto emissoesAtualizacaoDto, Usuario usuario) {
        Emissoes emissoes = emissoesRepository.getReferenceById(id);
        if (usuario.getRole() != UsuarioRole.ADMIN && !emissoes.getUsuario().equals(usuario)) {
            throw new AccessDeniedException("Usuário não tem permissão para atualizar esta emissão");
        }

        if (emissoesAtualizacaoDto.idFonte() != null) {
            Fontes fontes = fontesRepository.findById(emissoesAtualizacaoDto.idFonte()).orElseThrow(() -> new EntityNotFoundException("Fonte não encontrada"));
            emissoes.setFontes(fontes);
        }

        emissoes.atualizarInformacoes(emissoesAtualizacaoDto);
        return new EmissoesExibicaoDto(emissoes);
    }

    public Emissoes gravarEmissoes(Emissoes emissoes){
        return emissoesRepository.save(emissoes);
    }
}
