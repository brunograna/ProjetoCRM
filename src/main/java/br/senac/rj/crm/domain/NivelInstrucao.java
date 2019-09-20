package br.senac.rj.crm.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Where(clause="instrucao_status=1")
@Entity
public class NivelInstrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nivelInstrucaoId;

    @NotBlank
    private String nivelInstrucaoDescricao;

    @NotNull
    @Column(name="instrucao_status")
    private boolean nivelInstrucaoStatus;

    public Integer getNivelInstrucaoId() {
        return nivelInstrucaoId;
    }

    public void setNivelInstrucaoId(Integer nivelInstrucaoId) {
        this.nivelInstrucaoId = nivelInstrucaoId;
    }

    public String getNivelInstrucaoDescricao() {
        return nivelInstrucaoDescricao;
    }

    public void setNivelInstrucaoDescricao(String nivelInstrucaoDescricao) {
        this.nivelInstrucaoDescricao = nivelInstrucaoDescricao;
    }

    public boolean getNivelInstrucaoStatus() {
        return nivelInstrucaoStatus;
    }

    public void setNivelInstrucaoStatus(boolean nivelInstrucaoStatus) {
        this.nivelInstrucaoStatus = nivelInstrucaoStatus;
    }
}
