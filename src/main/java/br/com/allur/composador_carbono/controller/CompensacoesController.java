package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.models.Compensacoes;
import br.com.allur.composador_carbono.service.CompensacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compensacoes")
public class CompensacoesController {

    @Autowired
    private CompensacoesService compensacoesService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Compensacoes gravarCompensacoes(@RequestBody Compensacoes compensacoes){
        return compensacoesService.gravarCompensacoes(compensacoes);
    }
}
