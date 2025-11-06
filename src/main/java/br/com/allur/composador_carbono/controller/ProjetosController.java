package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.dto.ProjetosAtualizacaoDto;
import br.com.allur.composador_carbono.dto.ProjetosCadastroDto;
import br.com.allur.composador_carbono.dto.ProjetosExibicaoDto;
import br.com.allur.composador_carbono.service.ProjetosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projetos")
public class ProjetosController {

    @Autowired
    private ProjetosService projetosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetosExibicaoDto criar(@RequestBody @Valid ProjetosCadastroDto dto) {
        return projetosService.criar(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProjetosExibicaoDto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return projetosService.listar(paginacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetosExibicaoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProjetosAtualizacaoDto dto) {
        ProjetosExibicaoDto projetoAtualizado = projetosService.atualizar(id, dto);
        return ResponseEntity.ok(projetoAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        projetosService.excluir(id);
    }
}
