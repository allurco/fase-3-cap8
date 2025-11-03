package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.models.Alertas;
import br.com.allur.composador_carbono.service.AlertasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertas")
public class AlertasController {

    @Autowired
    private AlertasService alertasService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alertas gravarAlertas(@RequestBody Alertas alertas){
        return alertasService.gravarAlertas(alertas);
    }
}
