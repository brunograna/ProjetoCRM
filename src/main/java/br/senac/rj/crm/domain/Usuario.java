package br.senac.rj.crm.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    private String usuarioNome;

    @Column(nullable = false, unique = true)
    private String usuarioLogin;

    private String usuarioSenha;
    private String usuarioCargo;
    private boolean usuarioStatus;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    private List<PerfilUsuario> perfisUsuario = new ArrayList<>();

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public String getUsuarioCargo() {
        return usuarioCargo;
    }

    public void setUsuarioCargo(String usuarioCargo) {
        this.usuarioCargo = usuarioCargo;
    }

    public boolean getUsuarioStatus() {
        return usuarioStatus;
    }

    public void setUsuarioStatus(boolean usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    public List<PerfilUsuario> getPerfisUsuario() {
        return perfisUsuario;
    }

    public void setPerfisUsuario(List<PerfilUsuario> perfisUsuario) {
        this.perfisUsuario = perfisUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfisUsuario;
    }

    @Override
    public String getPassword() {
        return this.getUsuarioSenha();
    }

    @Override
    public String getUsername() {
        return this.getUsuarioLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.usuarioStatus;
    }
}