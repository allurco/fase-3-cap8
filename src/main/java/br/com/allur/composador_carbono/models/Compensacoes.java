package br.com.allur.composador_carbono.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_COMPENSACOES")
public class Compensacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPENSACOES")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMISSOES_FK")
    private Emissoes emissao;                       // Aqui fiz uma composição pra puxar os dados da FK


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROJETOS_FK")
    private Projetos projeto;                       // Aqui fiz uma composição pra puxar os dados da FK



    @Column(name = "QUANTIDADE_KG_CO2E")
    private Double quantidade;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;


    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
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
