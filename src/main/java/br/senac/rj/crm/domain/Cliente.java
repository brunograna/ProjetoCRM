package br.senac.rj.crm.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    private String clienteCpf;
    private String clienteNome;

    @OneToMany(
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClienteDado> clienteDado = new ArrayList<>();

    public Cliente() {
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public List<ClienteDado> getClienteDado() {
        return clienteDado;
    }

    public void setClienteDado(List<ClienteDado> clienteDado) {
        this.clienteDado = clienteDado;
    }

    public void addClienteDado(ClienteDado clienteDado){
        clienteDado.setCliente(this);
        this.clienteDado.add(clienteDado);
    }

    public void removeClienteDado(ClienteDado clienteDado){
        clienteDado.setCliente(null);
        this.clienteDado.remove(clienteDado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(clienteId, cliente.clienteId) &&
                Objects.equals(clienteCpf, cliente.clienteCpf) &&
                Objects.equals(clienteNome, cliente.clienteNome) &&
                Objects.equals(clienteDado, cliente.clienteDado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, clienteCpf, clienteNome, clienteDado);
    }
}