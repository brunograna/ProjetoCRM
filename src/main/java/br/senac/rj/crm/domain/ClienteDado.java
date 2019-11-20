package br.senac.rj.crm.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClienteDado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteDadoId;

    private String clienteDadoValor;

    @NotNull
    @Column(name = "cliente_dado_status")
    private boolean clienteDadoStatus;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "dado_tipo_id")
    private DadoTipo dadoTipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
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

    public boolean getClienteDadoStatus() {
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