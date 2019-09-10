package br.senac.rj.crm.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PerfilUsuario implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer perfilUsuarioId;

    private String perfilUsuarioDescricao;
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

    public boolean isPerfilUsuarioStatus() {
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
