package br.com.allur.composador_carbono.services;

import br.com.allur.composador_carbono.repositories.CompensacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensacoesService {

    @Autowired
    private CompensacoesRepository compensacoesRepository;

}
