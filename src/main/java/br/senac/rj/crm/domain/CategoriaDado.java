package br.senac.rj.crm.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Where(clause = "categoria_dado_status")
public class CategoriaDado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoriaDadoId;

    private String categoriaDadoDescricao;

    @Column(name = "categoria_dado_status")
    private boolean categoriaDadoStatus;

    public CategoriaDado() {
    }

    public Integer getCategoriaDadoId() {
        return categoriaDadoId;
    }

    public void setCategoriaDadoId(Integer categoriaDadoId) {
        this.categoriaDadoId = categoriaDadoId;
    }

    public String getCategoriaDadoDescricao() {
        return categoriaDadoDescricao;
    }

    public void setCategoriaDadoDescricao(String categoriaDadoDescricao) {
        this.categoriaDadoDescricao = categoriaDadoDescricao;
    }

    public boolean getCategoriaDadoStatus() {
        return categoriaDadoStatus;
    }

    public void setCategoriaDadoStatus(boolean categoriaDadoStatus) {
        this.categoriaDadoStatus = categoriaDadoStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDado that = (CategoriaDado) o;
        return categoriaDadoStatus == that.categoriaDadoStatus &&
                Objects.equals(categoriaDadoId, that.categoriaDadoId) &&
                Objects.equals(categoriaDadoDescricao, that.categoriaDadoDescricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriaDadoId, categoriaDadoDescricao, categoriaDadoStatus);
    }
}