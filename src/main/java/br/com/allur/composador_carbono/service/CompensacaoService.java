package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.dto.CompensacaoExibicaoDto;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.models.UsuarioRole;
import br.com.allur.composador_carbono.repository.CompensacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CompensacaoService {

    @Autowired
    private CompensacoesRepository compensacoesRepository;

    public Page<CompensacaoExibicaoDto> listar(Pageable pageable, Usuario usuario) {
        if (usuario.getRole() != UsuarioRole.ADMIN) {
            throw new AccessDeniedException("Acesso negado. Apenas administradores podem visualizar as compensações.");
        }
        return compensacoesRepository.findAll(pageable).map(CompensacaoExibicaoDto::new);
    }
}
