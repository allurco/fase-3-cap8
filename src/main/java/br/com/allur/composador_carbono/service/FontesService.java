package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.models.Fontes;
import br.com.allur.composador_carbono.repository.FontesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FontesService {

    @Autowired
    FontesRepository fontesRepository;

    public Fontes gravarFontes(Fontes fontes){
       return fontesRepository.save(fontes);
    }
}
