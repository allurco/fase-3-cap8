package br.com.allur.composador_carbono.models;

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


    @Column(name = "QUANTIDADE_KG_CO2E")
    private Double quantidade;

    private String categoria;

    @Column(name = "DATA_HORA")
    private LocalDate dataHora;

    private String metadados;


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
        if (o == null || getClass() != o.getClass()) return false;
        Emissoes emissoes = (Emissoes) o;
        return Objects.equals(id, emissoes.id) && Objects.equals(fontes, emissoes.fontes) && Objects.equals(quantidade, emissoes.quantidade) && Objects.equals(categoria, emissoes.categoria) && Objects.equals(dataHora, emissoes.dataHora) && Objects.equals(metadados, emissoes.metadados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fontes, quantidade, categoria, dataHora, metadados);
    }
}
