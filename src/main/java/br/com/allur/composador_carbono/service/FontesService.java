package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.dto.FontesCadastroDto;
import br.com.allur.composador_carbono.dto.FontesExibicaoDto;
import br.com.allur.composador_carbono.exception.FonteNaoEncontradaException;
import br.com.allur.composador_carbono.models.Fontes;
import br.com.allur.composador_carbono.repository.FontesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FontesService {

    @Autowired
    FontesRepository fontesRepository;

    public FontesExibicaoDto gravarFontes(FontesCadastroDto fontesCadastroDto) {
        Fontes fontes = new Fontes();
        BeanUtils.copyProperties(fontesCadastroDto, fontes);
        return new FontesExibicaoDto(fontesRepository.save(fontes));
    }

    public List<Fontes> listarFontes() {
        return fontesRepository.findAll();
    }

    public void excluirFontes(Long id) {
        fontesRepository.deleteById(id);
    }


    public Fontes atualizarFontes(Fontes fontes) {
        return fontesRepository.save(fontes);
    }


    public FontesExibicaoDto exibirFontesPorId(Long id) {
        Optional<Fontes> fontesOptional = fontesRepository.findById(id);

        if (fontesOptional.isPresent()) {
            return new FontesExibicaoDto(fontesOptional.get());
        } else {
            throw new FonteNaoEncontradaException("Fonte não encontrada");
        }
    }


    public List<FontesExibicaoDto> listarFontesPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return fontesRepository
                .listarFontesPorPeriodo(dataInicio, dataFim)
                .stream()
                .map(FontesExibicaoDto::new)
                .toList();
    }

    public FontesExibicaoDto buscarFontePorLocalizacao (String localizacao){
        Optional<Fontes> fontesOptional = fontesRepository.findByLocalizacao(localizacao);
        if(fontesOptional.isPresent()){
            return new FontesExibicaoDto(fontesOptional.get());
        } else {
            throw new FonteNaoEncontradaException("Localização de fonte não encontrada");
        }
    }
}
