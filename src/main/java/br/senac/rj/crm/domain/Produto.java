package br.senac.rj.crm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer produtoId;

    private String produtoDescricao;
    private boolean produtoStatus;

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

    public boolean isProdutoStatus() {
        return produtoStatus;
    }

    public void setProdutoStatus(boolean produtoStatus) {
        this.produtoStatus = produtoStatus;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "produtoId=" + produtoId +
                ", produtoDescricao='" + produtoDescricao + '\'' +
                ", produtoStatus=" + produtoStatus +
                '}';
    }
}
