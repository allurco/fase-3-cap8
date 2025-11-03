package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.models.Alertas;
import br.com.allur.composador_carbono.repository.AlertasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertasService {

    @Autowired
    private AlertasRepository alertasRepository;

    public Alertas gravarAlertas(Alertas alertas){
        return alertasRepository.save(alertas);
    }

}
