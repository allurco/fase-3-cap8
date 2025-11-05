package br.com.allur.composador_carbono.models;

import br.com.allur.composador_carbono.dto.EmissoesAtualizacaoDto;
import br.com.allur.composador_carbono.dto.EmissoesCadastroDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_EMISSOES")
public class Emissoes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_EMISSOES")
    @SequenceGenerator(name = "SEQ_ID_EMISSOES", sequenceName = "SEQ_ID_EMISSOES", allocationSize = 1)
    @Column(name = "ID_EMISSOES")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FONTES_FK")
    private Fontes fontes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_FK")
    private Usuario usuario;

    @Column(name = "QUANTIDADE_KG_CO2E")
    private Double quantidade;

    private String categoria;

    @Column(name = "DATA_HORA")
    private LocalDate dataHora;

    private String metadados;

    public Emissoes() {
    }

    public Emissoes(EmissoesCadastroDto dados, Usuario usuario) {
        this.usuario = usuario;
        this.quantidade = dados.quantidadeKgCo2e().doubleValue();
        this.categoria = dados.categoria();
        this.dataHora = dados.dataHora().toLocalDate();
        this.metadados = dados.metadados();
    }

    public void atualizarInformacoes(EmissoesAtualizacaoDto dados) {
        if (dados.quantidadeKgCo2e() != null) {
            this.quantidade = dados.quantidadeKgCo2e().doubleValue();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
        if (dados.dataHora() != null) {
            this.dataHora = dados.dataHora().toLocalDate();
        }
        if (dados.metadados() != null) {
            this.metadados = dados.metadados();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fontes getFontes() {
        return fontes;
    }

    public void setFontes(Fontes fontes) {
        this.fontes = fontes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public String getMetadados() {
        return metadados;
    }

    public void setMetadados(String metadados) {
        this.metadados = metadados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emissoes emissoes = (Emissoes) o;
        return Objects.equals(id, emissoes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
