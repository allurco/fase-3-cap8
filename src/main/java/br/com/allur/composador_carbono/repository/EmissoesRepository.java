package br.com.allur.composador_carbono.repository;

import br.com.allur.composador_carbono.models.Emissoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmissoesRepository extends JpaRepository<Emissoes, Long> {
}
