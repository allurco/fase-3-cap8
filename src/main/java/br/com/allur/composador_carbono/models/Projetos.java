package br.com.allur.composador_carbono.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_PROJETOS")
public class Projetos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PROJETOS")
    @SequenceGenerator(name = "SEQ_ID_PROJETOS", sequenceName = "SEQ_ID_PROJETOS", allocationSize = 1)
    @Column(name = "ID_PROJETOS")
    private Long id;


    private String nome;

    private String tipo;

    @Column(name = "CAPACIDADE_KG_CO2E")
    private Double capacidade;

    private String localizacao;

    @Column(name = "ANO_REFERENCIA")
    private LocalDate anoReferencia;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDate getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(LocalDate anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Projetos projetos = (Projetos) o;
        return Objects.equals(id, projetos.id) && Objects.equals(nome, projetos.nome) && Objects.equals(tipo, projetos.tipo) && Objects.equals(capacidade, projetos.capacidade) && Objects.equals(localizacao, projetos.localizacao) && Objects.equals(anoReferencia, projetos.anoReferencia) && Objects.equals(status, projetos.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipo, capacidade, localizacao, anoReferencia, status);
    }
}
