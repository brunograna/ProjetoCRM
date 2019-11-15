package br.senac.rj.crm.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AcaoUsuarioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acaoUsuarioClienteId;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;

    @Length(max = 250)
    private String acaoUsuarioClienteDescricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    private LocalDate acaoUsuarioClienteData;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Integer getAcaoUsuarioClienteId() {
        return acaoUsuarioClienteId;
    }

    public void setAcaoUsuarioClienteId(Integer acaoUsuarioClienteId) {
        this.acaoUsuarioClienteId = acaoUsuarioClienteId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public String getAcaoUsuarioClienteDescricao() {
        return acaoUsuarioClienteDescricao;
    }

    public void setAcaoUsuarioClienteDescricao(String acaoUsuarioClienteDescricao) {
        this.acaoUsuarioClienteDescricao = acaoUsuarioClienteDescricao;
    }

    public LocalDate getAcaoUsuarioClienteData() {
        return acaoUsuarioClienteData;
    }

    public void setAcaoUsuarioClienteData(LocalDate acaoUsuarioClienteData) {
        this.acaoUsuarioClienteData = acaoUsuarioClienteData;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
