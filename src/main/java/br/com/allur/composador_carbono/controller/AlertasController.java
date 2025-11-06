package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.dto.AlertaExibicaoDto;
import br.com.allur.composador_carbono.models.Alertas;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.service.AlertasService;
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
@RequestMapping("/api/alertas")
public class AlertasController {

    @Autowired
    private AlertasService alertasService;

    @PostMapping
    public ResponseEntity<Alertas> gravarAlertas(@RequestBody Alertas alertas, UriComponentsBuilder uriBuilder){
        Alertas alertaSalvo = alertasService.gravarAlertas(alertas);
        URI uri = uriBuilder.path("/api/alertas/{id}").buildAndExpand(alertaSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(alertaSalvo);
    }

    @GetMapping
    public ResponseEntity<Page<AlertaExibicaoDto>> listar(
            @PageableDefault(size = 10, sort = {"criadoEm"}) Pageable pageable,
            @AuthenticationPrincipal Usuario usuario) {
        Page<AlertaExibicaoDto> page = alertasService.listar(pageable, usuario);
        return ResponseEntity.ok(page);
    }
}
