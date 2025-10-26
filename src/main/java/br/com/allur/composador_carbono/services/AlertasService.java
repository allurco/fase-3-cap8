package br.com.allur.composador_carbono.services;

import br.com.allur.composador_carbono.repositories.AlertasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertasService {

    @Autowired
    private AlertasRepository alertasRepository;
}
