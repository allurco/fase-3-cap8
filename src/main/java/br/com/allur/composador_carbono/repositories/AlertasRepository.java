package br.com.allur.composador_carbono.repositories;

import br.com.allur.composador_carbono.models.Alertas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertasRepository extends JpaRepository<Alertas, Long> {
}
