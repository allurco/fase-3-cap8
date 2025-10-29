package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.models.Compensacoes;
import br.com.allur.composador_carbono.repository.CompensacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensacoesService {

    @Autowired
    private CompensacoesRepository compensacoesRepository;

    public Compensacoes gravarCompensacoes(Compensacoes compensacoes){
        return compensacoesRepository.save(compensacoes);
    }
}
