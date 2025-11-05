package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.dto.AlertaExibicaoDto;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.service.AlertaService;
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
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping
    public ResponseEntity<Page<AlertaExibicaoDto>> listar(
            @PageableDefault(size = 10, sort = {"criadoEm"}) Pageable pageable,
            @AuthenticationPrincipal Usuario usuario) {
        Page<AlertaExibicaoDto> page = alertaService.listar(pageable, usuario);
        return ResponseEntity.ok(page);
    }
}
