package br.com.allur.composador_carbono.repositories;

import br.com.allur.composador_carbono.models.Fontes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FontesRepository extends JpaRepository<Fontes, Long> {
}
