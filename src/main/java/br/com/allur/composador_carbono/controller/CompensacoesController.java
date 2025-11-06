package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.dto.CompensacaoExibicaoDto;
import br.com.allur.composador_carbono.models.Compensacoes;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.service.CompensacoesService;
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
@RequestMapping("/compensacoes")
public class CompensacoesController {

    @Autowired
    private CompensacoesService compensacoesService;

    @PostMapping
    public ResponseEntity<Compensacoes> gravarCompensacoes(@RequestBody Compensacoes compensacoes, UriComponentsBuilder uriBuilder){
        Compensacoes compensacaoSalva = compensacoesService.gravarCompensacoes(compensacoes);
        URI uri = uriBuilder.path("/compensacoes/{id}").buildAndExpand(compensacaoSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(compensacaoSalva);
    }

    @GetMapping
    public ResponseEntity<Page<CompensacaoExibicaoDto>> listar(
            @PageableDefault(size = 10, sort = {"data"}) Pageable pageable,
            @AuthenticationPrincipal Usuario usuario) {
        Page<CompensacaoExibicaoDto> page = compensacoesService.listar(pageable, usuario);
        return ResponseEntity.ok(page);
    }
}
