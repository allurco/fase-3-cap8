package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.dto.EmissoesAtualizacaoDto;
import br.com.allur.composador_carbono.dto.EmissoesCadastroDto;
import br.com.allur.composador_carbono.dto.EmissoesExibicaoDto;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.service.EmissoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/emissoes")
public class EmissoesController {

    @Autowired
    private EmissoesService emissoesService;

    @PostMapping
    public ResponseEntity<EmissoesExibicaoDto> criar(@RequestBody @Valid EmissoesCadastroDto emissoesCadastroDto, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder) {
        EmissoesExibicaoDto emissoesExibicaoDto = emissoesService.criar(emissoesCadastroDto, usuario);
        URI uri = uriBuilder.path("/emissoes/{id}").buildAndExpand(emissoesExibicaoDto.id()).toUri();
        return ResponseEntity.created(uri).body(emissoesExibicaoDto);
    }

    @GetMapping
    public ResponseEntity<Page<EmissoesExibicaoDto>> listar(@PageableDefault(size = 10, sort = {"dataHora"}) Pageable pageable, @AuthenticationPrincipal Usuario usuario) {
        Page<EmissoesExibicaoDto> page = emissoesService.listar(pageable, usuario);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmissoesExibicaoDto> atualizar(@PathVariable Long id, @RequestBody @Valid EmissoesAtualizacaoDto emissoesAtualizacaoDto, @AuthenticationPrincipal Usuario usuario) {
        EmissoesExibicaoDto emissoesExibicaoDto = emissoesService.atualizar(id, emissoesAtualizacaoDto, usuario);
        return ResponseEntity.ok(emissoesExibicaoDto);
    }
}
