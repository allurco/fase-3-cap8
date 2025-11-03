package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.models.Emissoes;
import br.com.allur.composador_carbono.service.EmissoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emissoes")
public class EmissoesController {

    @Autowired
    private EmissoesService emissoesService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emissoes gravarEmissoes(@RequestBody Emissoes emissoes) {
        return emissoesService.gravarEmissoes(emissoes);
    }
}
