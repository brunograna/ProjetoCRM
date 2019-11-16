package br.senac.rj.crm.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClienteOferta implements Serializable {

    @EmbeddedId
    @Valid
    private ClienteOfertaId clienteOfertaId;

    private double clienteOfertaPreco;

    @Size(max = 45)
    private String clienteOfertaPrecoDescricao;

    private boolean clienteOfertaStatus;

    @ManyToOne
    @JoinColumn(name = "funil_etapa_id")
    @NotNull
    private FunilEtapa funilEtapa;

    public ClienteOfertaId getClienteOfertaId() {
        return clienteOfertaId;
    }

    public void setClienteOfertaId(ClienteOfertaId clienteOfertaId) {
        this.clienteOfertaId = clienteOfertaId;
    }

    public double getClienteOfertaPreco() {
        return clienteOfertaPreco;
    }

    public void setClienteOfertaPreco(double clienteOfertaPreco) {
        this.clienteOfertaPreco = clienteOfertaPreco;
    }

    public String getClienteOfertaPrecoDescricao() {
        return clienteOfertaPrecoDescricao;
    }

    public void setClienteOfertaPrecoDescricao(String clienteOfertaPrecoDescricao) {
        this.clienteOfertaPrecoDescricao = clienteOfertaPrecoDescricao;
    }

    public boolean getClienteOfertaStatus() {
        return clienteOfertaStatus;
    }

    public void setClienteOfertaStatus(boolean clienteOfertaStatus) {
        this.clienteOfertaStatus = clienteOfertaStatus;
    }

    public FunilEtapa getFunilEtapa() {
        return funilEtapa;
    }

    public void setFunilEtapa(FunilEtapa funilEtapa) {
        this.funilEtapa = funilEtapa;
    }

}
