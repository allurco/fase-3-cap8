package br.com.allur.composador_carbono.repository;

import br.com.allur.composador_carbono.models.Fontes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FontesRepository extends JpaRepository<Fontes, Long> {

    @Query("SELECT f FROM Fontes f WHERE f.dataCriacao BETWEEN :dataInicio AND :dataFim ")
    List<Fontes> listarFontesPorPeriodo(
           @Param("dataInicio") LocalDate dataInicio,
           @Param("dataFim") LocalDate dataFim
    );


    Optional<Fontes> findByLocalizacao(String localizacao);
}
