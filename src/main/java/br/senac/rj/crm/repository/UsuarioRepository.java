package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    long count();

    Optional<Usuario> findByUsuarioLogin(String login);

}
