package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.dto.CompensacaoExibicaoDto;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.service.CompensacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compensacoes")
public class CompensacaoController {

    @Autowired
    private CompensacaoService compensacaoService;

    @GetMapping
    public ResponseEntity<Page<CompensacaoExibicaoDto>> listar(
            @PageableDefault(size = 10, sort = {"data"}) Pageable pageable,
            @AuthenticationPrincipal Usuario usuario) {
        Page<CompensacaoExibicaoDto> page = compensacaoService.listar(pageable, usuario);
        return ResponseEntity.ok(page);
    }
}
