package br.senac.rj.crm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acaoId;

    @NotEmpty
    private String acaoDescricao;

    private boolean acaoStatus;

    public Integer getAcaoId() {
        return acaoId;
    }

    public void setAcaoId(Integer acaoId) {
        this.acaoId = acaoId;
    }

    public String getAcaoDescricao() {
        return acaoDescricao;
    }

    public void setAcaoDescricao(String acaoDescricao) {
        this.acaoDescricao = acaoDescricao;
    }

    public boolean getAcaoStatus() {
        return acaoStatus;
    }

    public void setAcaoStatus(boolean acaoStatus) {
        this.acaoStatus = acaoStatus;
    }

}