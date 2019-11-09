package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.AcaoUsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoUsuarioClienteRepository extends JpaRepository<AcaoUsuarioCliente, Integer> {
}
