package br.senac.rj.crm.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Where(clause="produto_status=1")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer produtoId;

    @NotBlank
    private String produtoDescricao;

    @NotNull
    @Column(name="produto_status")
    private boolean produtoStatus;

    @ManyToOne
    @JoinColumn(name = "nivel_instrucao_id")
    private NivelInstrucao nivelInstrucao;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public boolean getProdutoStatus() {
        return produtoStatus;
    }

    public void setProdutoStatus(boolean produtoStatus) {
        this.produtoStatus = produtoStatus;
    }

    public NivelInstrucao getNivelInstrucao() { return nivelInstrucao; }

    public void setNivelInstrucao(NivelInstrucao nivelInstrucao) { this.nivelInstrucao = nivelInstrucao; }

    @Override
    public String toString() {
        return "Produto{" +
                "produtoId=" + produtoId +
                ", produtoDescricao='" + produtoDescricao + '\'' +
                ", produtoStatus=" + produtoStatus +
                '}';
    }
}
