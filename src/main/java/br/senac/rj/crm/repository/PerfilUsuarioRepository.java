package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {
    List<PerfilUsuario> findByPerfilUsuarioStatus(boolean status);
}
