package br.senac.rj.crm.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

//@Where(clause="dado_tipo_status=1")
@Entity
public class DadoTipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dadoTipoId;

    @NotBlank
    private String dadoTipoDescricao;

    @NotBlank
    @Column(name = "dado_tipo_status")
    private boolean dadoTipoStatus;

    private boolean dadoTipoObrigatorio;
    private String dadoTipoPadrao;
    private String dadoTipoMascara;

    public DadoTipo() {
    }

    @ManyToOne
    @JoinColumn(name = "categoria_dado_id")
    private CategoriaDado categoria;

    public Integer getDadoTipoId() {
        return dadoTipoId;
    }

    public void setDadoTipoId(Integer dadoTipoId) {
        this.dadoTipoId = dadoTipoId;
    }

    public String getDadoTipoDescricao() {
        return dadoTipoDescricao;
    }

    public void setDadoTipoDescricao(String dadoTipoDescricao) {
        this.dadoTipoDescricao = dadoTipoDescricao;
    }

    public boolean getDadoTipoStatus() {
        return dadoTipoStatus;
    }

    public void setDadoTipoStatus(boolean dadoTipoStatus) {
        this.dadoTipoStatus = dadoTipoStatus;
    }

    public boolean getDadoTipoObrigatorio() {
        return dadoTipoObrigatorio;
    }

    public void setDadoTipoObrigatorio(boolean dadoTipoObrigatorio) {
        this.dadoTipoObrigatorio = dadoTipoObrigatorio;
    }

    public String getDadoTipoPadrao() {
        return dadoTipoPadrao;
    }

    public void setDadoTipoPadrao(String dadoTipoPadrao) {
        this.dadoTipoPadrao = dadoTipoPadrao;
    }

    public String getDadoTipoMascara() {
        return dadoTipoMascara;
    }

    public void setDadoTipoMascara(String dadoTipoMascara) {
        this.dadoTipoMascara = dadoTipoMascara;
    }

    public CategoriaDado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDado categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadoTipo dadoTipo = (DadoTipo) o;
        return dadoTipoStatus == dadoTipo.dadoTipoStatus &&
                dadoTipoObrigatorio == dadoTipo.dadoTipoObrigatorio &&
                Objects.equals(dadoTipoId, dadoTipo.dadoTipoId) &&
                Objects.equals(dadoTipoDescricao, dadoTipo.dadoTipoDescricao) &&
                Objects.equals(dadoTipoPadrao, dadoTipo.dadoTipoPadrao) &&
                Objects.equals(dadoTipoMascara, dadoTipo.dadoTipoMascara) &&
                Objects.equals(categoria, dadoTipo.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dadoTipoId, dadoTipoDescricao, dadoTipoStatus, dadoTipoObrigatorio, dadoTipoPadrao, dadoTipoMascara, categoria);
    }
}