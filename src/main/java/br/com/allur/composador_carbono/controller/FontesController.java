package br.com.allur.composador_carbono.controller;


import br.com.allur.composador_carbono.dto.FontesCadastroDto;
import br.com.allur.composador_carbono.dto.FontesExibicaoDto;
import br.com.allur.composador_carbono.models.Fontes;
import br.com.allur.composador_carbono.service.FontesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fontes")
public class FontesController {

    @Autowired
    FontesService fontesService;

    @PostMapping
    public ResponseEntity<FontesExibicaoDto> criar(@RequestBody @Valid FontesCadastroDto fontesCadastroDto, UriComponentsBuilder uriBuilder) {
        FontesExibicaoDto fontesCriada = fontesService.gravarFontes(fontesCadastroDto);
        URI uri = uriBuilder.path("/api/fontes/{id}").buildAndExpand(fontesCriada.id()).toUri();
        return ResponseEntity.created(uri).body(fontesCriada);
    }

    @GetMapping
    public ResponseEntity<Page<FontesExibicaoDto>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        Page<FontesExibicaoDto> page = fontesService.listarFontes(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FontesExibicaoDto> buscarPorId(@PathVariable Long id) {
        FontesExibicaoDto fonte = fontesService.exibirFontesPorId(id);
        return ResponseEntity.ok(fonte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fontes> atualizar(@PathVariable Long id, @RequestBody Fontes fontes) {
        fontes.setId(id); // Ensure the ID from path is used
        Fontes fonteAtualizada = fontesService.atualizarFontes(fontes);
        return ResponseEntity.ok(fonteAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        fontesService.excluirFontes(id);
        return ResponseEntity.noContent().build();
    }

    // Custom query endpoints
    @GetMapping(value = "/periodo", params = {"dataInicio", "dataFim"})
    public ResponseEntity<List<FontesExibicaoDto>> listarPorPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    ) {
        List<FontesExibicaoDto> fontes = fontesService.listarFontesPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(fontes);
    }

    @GetMapping(value = "/buscar", params = "localizacao")
    public ResponseEntity<FontesExibicaoDto> buscarPorLocalizacao(@Param("localizacao") String localizacao){
        FontesExibicaoDto fonte = fontesService.buscarFontePorLocalizacao(localizacao);
        return ResponseEntity.ok(fonte);
    }
}
