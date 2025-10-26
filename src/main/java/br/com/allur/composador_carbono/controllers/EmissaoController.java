package br.com.allur.composador_carbono.controllers;

import br.com.allur.composador_carbono.models.Emissoes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emissao")
public class EmissaoController {

    @GetMapping("/emissoes")
    public Emissoes getEmissoes(){
        Emissoes emissoes = new Emissoes();
    }
}
