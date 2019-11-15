package br.senac.rj.crm.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FunilEtapa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer funilEtapaId;

    @Size(max = 100)
    private String funilEtapaDescricao;

    private boolean funilEtapaStatus;

    public Integer getFunilEtapaId() {
        return funilEtapaId;
    }

    public void setFunilEtapaId(Integer funilEtapaId) {
        this.funilEtapaId = funilEtapaId;
    }

    public String getFunilEtapaDescricao() {
        return funilEtapaDescricao;
    }

    public void setFunilEtapaDescricao(String funilEtapaDescricao) {
        this.funilEtapaDescricao = funilEtapaDescricao;
    }

    public boolean getFunilEtapaStatus() {
        return funilEtapaStatus;
    }

    public void setFunilEtapaStatus(boolean funilEtapaStatus) {
        this.funilEtapaStatus = funilEtapaStatus;
    }
}
