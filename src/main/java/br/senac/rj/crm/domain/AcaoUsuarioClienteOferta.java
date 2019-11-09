package br.senac.rj.crm.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AcaoUsuarioClienteOferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acaoUsuarioClienteOfertaId;

    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;

    @Length(max = 250)
    private String acaoUsuarioClienteOfertaDescricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    private LocalDate acaoUsuarioClienteOfertaData;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    private ClienteOferta clienteOferta;

    public Integer getAcaoUsuarioClienteOfertaId() {
        return acaoUsuarioClienteOfertaId;
    }

    public void setAcaoUsuarioClienteOfertaId(Integer acaoUsuarioClienteOfertaId) {
        this.acaoUsuarioClienteOfertaId = acaoUsuarioClienteOfertaId;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public String getAcaoUsuarioClienteOfertaDescricao() {
        return acaoUsuarioClienteOfertaDescricao;
    }

    public void setAcaoUsuarioClienteOfertaDescricao(String acaoUsuarioClienteOfertaDescricao) {
        this.acaoUsuarioClienteOfertaDescricao = acaoUsuarioClienteOfertaDescricao;
    }

    public LocalDate getAcaoUsuarioClienteOfertaData() {
        return acaoUsuarioClienteOfertaData;
    }

    public void setAcaoUsuarioClienteOfertaData(LocalDate acaoUsuarioClienteOfertaData) {
        this.acaoUsuarioClienteOfertaData = acaoUsuarioClienteOfertaData;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ClienteOferta getClienteOferta() {
        return clienteOferta;
    }

    public void setClienteOferta(ClienteOferta clienteOferta) {
        this.clienteOferta = clienteOferta;
    }
}
