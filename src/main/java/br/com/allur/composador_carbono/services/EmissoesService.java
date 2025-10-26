package br.com.allur.composador_carbono.services;

import br.com.allur.composador_carbono.repositories.EmissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissoesService {

    @Autowired
    private EmissoesRepository emissoesRepository;
}
