package br.com.allur.composador_carbono.controller;


import br.com.allur.composador_carbono.models.Fontes;
import br.com.allur.composador_carbono.service.FontesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fontes")
public class FontesController {

    @Autowired
    FontesService fontesService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fontes gravarFontes(@RequestBody Fontes fontes){
        return fontesService.gravarFontes(fontes);
    }


}
