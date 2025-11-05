package br.com.allur.composador_carbono.repository;

import br.com.allur.composador_carbono.models.Emissoes;
import br.com.allur.composador_carbono.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmissoesRepository extends JpaRepository<Emissoes, Long> {
    Page<Emissoes> findAllByUsuario(Usuario usuario, Pageable pageable);
}
