package br.com.allur.composador_carbono.service;


import br.com.allur.composador_carbono.models.Projetos;
import br.com.allur.composador_carbono.repository.ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetosService {

    @Autowired
    private ProjetosRepository projetosRepository;

    public Projetos gravarProjetos(Projetos projetos){
        return projetosRepository.save(projetos);
    }
}
