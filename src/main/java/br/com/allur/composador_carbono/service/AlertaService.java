package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.dto.AlertaExibicaoDto;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.models.UsuarioRole;
import br.com.allur.composador_carbono.repository.AlertasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    @Autowired
    private AlertasRepository alertasRepository;

    public Page<AlertaExibicaoDto> listar(Pageable pageable, Usuario usuario) {
        if (usuario.getRole() != UsuarioRole.ADMIN) {
            throw new AccessDeniedException("Acesso negado. Apenas administradores podem visualizar os alertas.");
        }
        return alertasRepository.findAll(pageable).map(AlertaExibicaoDto::new);
    }
}
