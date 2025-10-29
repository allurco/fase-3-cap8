package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.models.Projetos;
import br.com.allur.composador_carbono.service.ProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetos")
public class ProjetosController {

    @Autowired
    private ProjetosService projetosService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Projetos gravarProjetos(@RequestBody Projetos projetos){
        return projetosService.gravarProjetos(projetos);
    }
}
