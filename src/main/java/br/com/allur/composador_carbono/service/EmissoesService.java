package br.com.allur.composador_carbono.service;

import br.com.allur.composador_carbono.dto.EmissoesAtualizacaoDto;
import br.com.allur.composador_carbono.dto.EmissoesCadastroDto;
import br.com.allur.composador_carbono.dto.EmissoesExibicaoDto;
import br.com.allur.composador_carbono.models.*;
import br.com.allur.composador_carbono.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmissoesService {

    @Autowired
    private EmissoesRepository emissoesRepository;
    @Autowired
    private FontesRepository fontesRepository;
    @Autowired
    private ProjetosRepository projetosRepository;
    @Autowired
    private CompensacoesRepository compensacoesRepository;
    @Autowired
    private AlertasRepository alertasRepository;

    @Transactional
    public EmissoesExibicaoDto criar(EmissoesCadastroDto emissoesCadastroDto, Usuario usuario) {
        double quantidadeEmissao = emissoesCadastroDto.quantidadeKgCo2e().doubleValue();

        List<Projetos> projetosAtivos = projetosRepository.findAll().stream()
                .filter(p -> "ATIVO".equalsIgnoreCase(p.getStatus()))
                .collect(Collectors.toList());

        double capacidadeTotal = projetosAtivos.stream().mapToDouble(Projetos::getCapacidade).sum();

        // Validação Unificada: Verifica se a capacidade é suficiente. Se não for, cria alerta e lança exceção.
        if (quantidadeEmissao > capacidadeTotal) {
            String mensagem = "Capacidade total insuficiente. Emissão de " + quantidadeEmissao + " kg CO2e excede a capacidade total de " + capacidadeTotal + " kg CO2e dos projetos ativos.";
            criarAlerta("Capacidade Insuficiente", mensagem);
            throw new IllegalStateException(mensagem);
        }

        // Se a validação passar, prossegue com a criação da emissão e compensação.
        Fontes fontes = fontesRepository.findById(emissoesCadastroDto.idFonte()).orElseThrow(() -> new EntityNotFoundException("Fonte não encontrada"));
        Emissoes emissoes = new Emissoes(emissoesCadastroDto, usuario);
        emissoes.setFontes(fontes);
        emissoesRepository.save(emissoes);

        double quantidadeEmissaoRestante = quantidadeEmissao;

        for (Projetos projeto : projetosAtivos) {
            if (quantidadeEmissaoRestante <= 0) break;

            double capacidadeProjeto = projeto.getCapacidade();
            double valorACompensar = Math.min(quantidadeEmissaoRestante, capacidadeProjeto);

            Compensacoes compensacao = new Compensacoes();
            compensacao.setEmissao(emissoes);
            compensacao.setProjeto(projeto);
            compensacao.setQuantidade(valorACompensar);
            compensacao.setDataHora(LocalDate.now());
            compensacoesRepository.save(compensacao);

            double novaCapacidade = capacidadeProjeto - valorACompensar;
            projeto.setCapacidade(novaCapacidade);
            if (novaCapacidade <= 0) {
                projeto.setStatus("FINALIZADO");
                criarAlerta("Projeto Finalizado", "O projeto '" + projeto.getNome() + "' foi finalizado.");
            }
            projetosRepository.save(projeto);

            quantidadeEmissaoRestante -= valorACompensar;
        }

        return new EmissoesExibicaoDto(emissoes);
    }

    private void criarAlerta(String tipo, String mensagem) {
        Alertas alerta = new Alertas();
        alerta.setTipo(tipo);
        alerta.setMensagem(mensagem);
        alerta.setCriadoEm(LocalDate.now());
        alertasRepository.save(alerta);
    }

    public Page<EmissoesExibicaoDto> listar(Pageable pageable, Usuario usuario) {
        if (usuario.getRole() == UsuarioRole.ADMIN) {
            return emissoesRepository.findAll(pageable).map(EmissoesExibicaoDto::new);
        } else {
            return emissoesRepository.findAllByUsuario(usuario, pageable).map(EmissoesExibicaoDto::new);
        }
    }

    @Transactional
    public EmissoesExibicaoDto atualizar(Long id, EmissoesAtualizacaoDto emissoesAtualizacaoDto, Usuario usuario) {
        Emissoes emissoes = emissoesRepository.getReferenceById(id);
        if (usuario.getRole() != UsuarioRole.ADMIN && !emissoes.getUsuario().equals(usuario)) {
            throw new AccessDeniedException("Usuário não tem permissão para atualizar esta emissão");
        }

        if (emissoesAtualizacaoDto.idFonte() != null) {
            Fontes fontes = fontesRepository.findById(emissoesAtualizacaoDto.idFonte()).orElseThrow(() -> new EntityNotFoundException("Fonte não encontrada"));
            emissoes.setFontes(fontes);
        }

        emissoes.atualizarInformacoes(emissoesAtualizacaoDto);
        return new EmissoesExibicaoDto(emissoes);
    }
}
