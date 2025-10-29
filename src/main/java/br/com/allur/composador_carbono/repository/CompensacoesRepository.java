package br.com.allur.composador_carbono.repository;

import br.com.allur.composador_carbono.models.Compensacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompensacoesRepository extends JpaRepository<Compensacoes, Long> {
}
