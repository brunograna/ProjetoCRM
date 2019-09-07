package br.senac.rj.crm.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ClienteDado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteDadoId;

    private String clienteDadoValor;
    private boolean clienteDadoStatus;
    @OneToOne
    private DadoTipo dadoTipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public ClienteDado() {
    }

    public Integer getClienteDadoId() {
        return clienteDadoId;
    }

    public void setClienteDadoId(Integer clienteDadoId) {
        this.clienteDadoId = clienteDadoId;
    }

    public String getClienteDadoValor() {
        return clienteDadoValor;
    }

    public void setClienteDadoValor(String clienteDadoValor) {
        this.clienteDadoValor = clienteDadoValor;
    }

    public boolean isClienteDadoStatus() {
        return clienteDadoStatus;
    }

    public void setClienteDadoStatus(boolean clienteDadoStatus) {
        this.clienteDadoStatus = clienteDadoStatus;
    }

    public DadoTipo getDadoTipo() {
        return dadoTipo;
    }

    public void setDadoTipo(DadoTipo dadoTipo) {
        this.dadoTipo = dadoTipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDado that = (ClienteDado) o;
        return clienteDadoStatus == that.clienteDadoStatus &&
                Objects.equals(clienteDadoId, that.clienteDadoId) &&
                Objects.equals(clienteDadoValor, that.clienteDadoValor) &&
                Objects.equals(dadoTipo, that.dadoTipo) &&
                Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteDadoId, clienteDadoValor, clienteDadoStatus, dadoTipo, cliente);
    }
}