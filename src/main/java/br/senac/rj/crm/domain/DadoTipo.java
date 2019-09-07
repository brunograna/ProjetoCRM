package br.senac.rj.crm.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class DadoTipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ddadoTipoId;

    private String dadoTipoDescricao;
    private boolean dadoTipoStatus;
    private boolean dadoTipoObrigatorio;
    private String dadoTipoPadrao;
    private String dadoTipoMascara;

    public DadoTipo() {
    }

    @ManyToOne
    private CategoriaDado categoria;

    public Integer getDdadoTipoId() {
        return ddadoTipoId;
    }

    public void setDdadoTipoId(Integer ddadoTipoId) {
        this.ddadoTipoId = ddadoTipoId;
    }

    public String getDadoTipoDescricao() {
        return dadoTipoDescricao;
    }

    public void setDadoTipoDescricao(String dadoTipoDescricao) {
        this.dadoTipoDescricao = dadoTipoDescricao;
    }

    public boolean isDadoTipoStatus() {
        return dadoTipoStatus;
    }

    public void setDadoTipoStatus(boolean dadoTipoStatus) {
        this.dadoTipoStatus = dadoTipoStatus;
    }

    public boolean isDadoTipoObrigatorio() {
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
                Objects.equals(ddadoTipoId, dadoTipo.ddadoTipoId) &&
                Objects.equals(dadoTipoDescricao, dadoTipo.dadoTipoDescricao) &&
                Objects.equals(dadoTipoPadrao, dadoTipo.dadoTipoPadrao) &&
                Objects.equals(dadoTipoMascara, dadoTipo.dadoTipoMascara) &&
                Objects.equals(categoria, dadoTipo.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddadoTipoId, dadoTipoDescricao, dadoTipoStatus, dadoTipoObrigatorio, dadoTipoPadrao, dadoTipoMascara, categoria);
    }
}