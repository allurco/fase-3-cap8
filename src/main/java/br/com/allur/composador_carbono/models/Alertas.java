package br.com.allur.composador_carbono.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_ALERTAS")
public class Alertas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ALERTAS")
    @SequenceGenerator(name = "SEQ_ID_ALERTAS", sequenceName = "SEQ_ID_ALERTAS", allocationSize = 1)
    @Column(name = "ID_ALERTAS")
    private Long id;


    private String tipo;

    @Column(name = "ID_RELACIONADO")
    private Integer idRelacionado;

    private String mensagem;

    private char resolvido;

    @Column(name = "CRIADO_EM")
    private LocalDate criadoEm;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdRelacionado() {
        return idRelacionado;
    }

    public void setIdRelacionado(Integer id_relacionado) {
        this.idRelacionado = id_relacionado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public char getResolvido() {
        return resolvido;
    }

    public void setResolvido(char resolvido) {
        this.resolvido = resolvido;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criado_em) {
        this.criadoEm = criado_em;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alertas alertas = (Alertas) o;
        return resolvido == alertas.resolvido && Objects.equals(id, alertas.id) && Objects.equals(tipo, alertas.tipo) && Objects.equals(idRelacionado, alertas.idRelacionado) && Objects.equals(mensagem, alertas.mensagem) && Objects.equals(criadoEm, alertas.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, idRelacionado, mensagem, resolvido, criadoEm);
    }
}
