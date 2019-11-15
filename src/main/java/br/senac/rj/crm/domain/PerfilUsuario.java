package br.senac.rj.crm.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PerfilUsuario implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissao_id")
    private Integer perfilUsuarioId;

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "permissao_descricao")
    private String perfilUsuarioDescricao;

    @NotNull
    @Column(name = "permissao_status")
    private boolean perfilUsuarioStatus;

    public Integer getPerfilUsuarioId() {
        return perfilUsuarioId;
    }

    public void setPerfilUsuarioId(Integer perfilUsuarioId) {
        this.perfilUsuarioId = perfilUsuarioId;
    }

    public String getPerfilUsuarioDescricao() {
        return perfilUsuarioDescricao;
    }

    public void setPerfilUsuarioDescricao(String perfilUsuarioDescricao) {
        this.perfilUsuarioDescricao = perfilUsuarioDescricao;
    }

    public boolean getPerfilUsuarioStatus() {
        return perfilUsuarioStatus;
    }

    public void setPerfilUsuarioStatus(boolean perfilUsuarioStatus) {
        this.perfilUsuarioStatus = perfilUsuarioStatus;
    }

    @Override
    public String getAuthority() {
        return this.perfilUsuarioDescricao;
    }
}
