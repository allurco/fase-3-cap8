package br.com.allur.composador_carbono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompensacoesRepository extends JpaRepository<CompensacoesRepository, Long> {
}
