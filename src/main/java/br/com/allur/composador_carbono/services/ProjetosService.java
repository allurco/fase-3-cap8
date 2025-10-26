package br.com.allur.composador_carbono.services;

import br.com.allur.composador_carbono.repositories.ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetosService {
    @Autowired
    private ProjetosRepository projetosRepository;
}
