package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.models.Emissoes;
import br.com.allur.composador_carbono.repository.EmissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissoesService {

    @Autowired
    private EmissoesRepository emissoesRepository;


    public Emissoes gravarEmissoes(Emissoes emissoes){
        return emissoesRepository.save(emissoes);
    }
}
