package br.com.allur.composador_carbono.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_COMPENSACOES")
public class Compensacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_COMPENSACOES")
    @SequenceGenerator(name = "SEQ_ID_COMPENSACOES", sequenceName = "SEQ_ID_COMPENSACOES", allocationSize = 1)
    @Column(name = "ID_COMPENSACOES")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMISSOES_FK")
    private Emissoes emissao;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROJETOS_FK")
    private Projetos projeto;


    @Column(name = "QUANTIDADE_KG_CO2E")
    private Double quantidade;

    @Column(name = "DATA_HORA")
    private LocalDate dataHora;


    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Emissoes getEmissao() {
        return emissao;
    }

    public void setEmissao(Emissoes emissao) {
        this.emissao = emissao;
    }

    public Projetos getProjeto() {
        return projeto;
    }

    public void setProjeto(Projetos projeto) {
        this.projeto = projeto;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Compensacoes that = (Compensacoes) o;
        return Objects.equals(id, that.id) && Objects.equals(emissao, that.emissao) && Objects.equals(projeto, that.projeto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(dataHora, that.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emissao, projeto, quantidade, dataHora);
    }
}
