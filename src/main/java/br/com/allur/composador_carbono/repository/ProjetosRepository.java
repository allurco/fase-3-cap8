package br.com.allur.composador_carbono.repository;

import br.com.allur.composador_carbono.models.Projetos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetosRepository extends JpaRepository<Projetos, Long> {
}
