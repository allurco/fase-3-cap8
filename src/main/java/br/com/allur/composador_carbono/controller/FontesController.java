package br.com.allur.composador_carbono.controller;


import br.com.allur.composador_carbono.dto.FontesCadastroDto;
import br.com.allur.composador_carbono.dto.FontesExibicaoDto;
import br.com.allur.composador_carbono.models.Fontes;
import br.com.allur.composador_carbono.service.FontesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/fontes")
public class FontesController {

    @Autowired
    FontesService fontesService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FontesExibicaoDto gravarFontes(@RequestBody @Valid FontesCadastroDto fontesCadastroDto) {
        return fontesService.gravarFontes(fontesCadastroDto);
    }


    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public Page<FontesExibicaoDto> listarFontes(Pageable paginacao) {
        return fontesService.listarFontes(paginacao);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FontesExibicaoDto exibirFontesPorId(@PathVariable Long id) {
        return fontesService.exibirFontesPorId(id);
    }


    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public Fontes atualizarFontes(@RequestBody Fontes fontes) {
        return fontesService.atualizarFontes(fontes);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirFonte(@PathVariable Long id) {
        fontesService.excluirFontes(id);
    }


    // api/fontes/listaPeriodo?dataInicio=ano-mes-dia&dataFinal=ano-mes-dia
    @GetMapping(value = "/listaPeriodo", params = {"dataInicio", "dataFim"})
    public List<FontesExibicaoDto> listarFontesPorPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    ) {
        return fontesService.listarFontesPorPeriodo(dataInicio, dataFim);
    }


    // api/fontes/localizacao?localizacao=...
    @GetMapping(value = "/localizacao", params = "localizacao")
    public FontesExibicaoDto exibirFontesPorLocalizacao(@Param("localizacao") String localizacao){
        return fontesService.buscarFontePorLocalizacao(localizacao);
    }
}
