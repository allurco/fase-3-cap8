package br.com.allur.composador_carbono.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_FONTES")
public class Fontes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FONTES")
    private Long id;

    private String nome;

    private String tipo;

    private String localizacao;

    @Column(name = "ID_RESPONSAVEL")
    private Long idResponsavel;

    @Column(name = "CRIADO_EM")
    private LocalDateTime dataCriacao;

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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Long getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Long id_responsavel) {
        this.idResponsavel = id_responsavel;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime criado_em) {
        this.dataCriacao = criado_em;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fontes fontes = (Fontes) o;
        return Objects.equals(id, fontes.id) && Objects.equals(nome, fontes.nome) && Objects.equals(tipo, fontes.tipo) && Objects.equals(localizacao, fontes.localizacao) && Objects.equals(idResponsavel, fontes.idResponsavel) && Objects.equals(dataCriacao, fontes.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipo, localizacao, idResponsavel, dataCriacao);
    }
}
