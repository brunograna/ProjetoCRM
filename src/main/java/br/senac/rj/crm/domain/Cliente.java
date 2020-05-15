package br.senac.rj.crm.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @NotBlank
    @Size(min = 11, max = 11)
    private String clienteCpf;

    @NotBlank
    private String clienteNome;

    private String clienteSobrenome;
    private String clienteEmail;

    @Column(name = "cliente_status")
    private boolean clienteStatus;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "cliente",
            cascade = CascadeType.ALL
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

    public String getClienteSobrenome() { return clienteSobrenome; }

    public void setClienteSobrenome(String clienteSobrenome) { this.clienteSobrenome = clienteSobrenome; }

    public String getClienteEmail() { return clienteEmail; }

    public void setClienteEmail(String clienteEmail) { this.clienteEmail = clienteEmail; }

    public boolean getClienteStatus() { return clienteStatus; }

    public void setClienteStatus(boolean clienteStatus) { this.clienteStatus = clienteStatus; }

    public List<ClienteDado> getClienteDado() {
        if (clienteDado == null) {
            clienteDado = new ArrayList<>();
        }
        return clienteDado;
    }

    public void setClienteDado(List<ClienteDado> clienteDados) {
        this.clienteDado = clienteDados;
    }

    public void cleanClienteDadoList(List<ClienteDado> clienteDados){
        if(clienteDados != null){
            List<ClienteDado> clienteDadoList = new ArrayList<>();
            for (ClienteDado clienteDadoEach : clienteDados) {
                if (!(clienteDadoEach.getDadoTipo() == null)){
                    clienteDadoEach.setCliente(this);
                    clienteDadoEach.setClienteDadoStatus(true);
                    clienteDadoList.add(clienteDadoEach);
                }
            }
            this.clienteDado = clienteDadoList;
        }
    }

    public void addClienteDado(ClienteDado clienteDado){
        clienteDado.setCliente(this);
        clienteDado.setClienteDadoStatus(true);
        this.clienteDado.add(clienteDado);
    }

    public void removeClienteDado(ClienteDado clienteDado){
        clienteDado.setCliente(null);
        this.clienteDado.remove(clienteDado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getClienteStatus() == cliente.getClienteStatus() &&
                Objects.equals(getClienteId(), cliente.getClienteId()) &&
                Objects.equals(getClienteCpf(), cliente.getClienteCpf()) &&
                Objects.equals(getClienteNome(), cliente.getClienteNome()) &&
                Objects.equals(getClienteSobrenome(), cliente.getClienteSobrenome()) &&
                Objects.equals(getClienteEmail(), cliente.getClienteEmail()) &&
                Objects.equals(getClienteDado(), cliente.getClienteDado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClienteId(), getClienteCpf(), getClienteNome(), getClienteSobrenome(), getClienteEmail(), getClienteStatus(), getClienteDado());
    }
}