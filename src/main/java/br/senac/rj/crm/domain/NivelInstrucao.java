package br.senac.rj.crm.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//@Where(clause="instrucao_status=1")
@Entity
public class NivelInstrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instrucaoId;

    @NotBlank
    private String instrucaoDescricao;

    @NotBlank
    @Column(name="instrucao_status")
    private boolean instrucaoStatus;

    public Integer getInstrucaoId() { return instrucaoId; }

    public void setInstrucaoId(Integer instrucaoId) { this.instrucaoId = instrucaoId; }

    public String getInstrucaoDescricao() { return instrucaoDescricao; }

    public void setInstrucaoDescricao(String instrucaoDescricao) {
        this.instrucaoDescricao = instrucaoDescricao;
    }

    public boolean getInstrucaoStatus() { return instrucaoStatus; }

    public void setInstrucaoStatus(boolean instrucaoStatus) { this.instrucaoStatus = instrucaoStatus; }
}
