package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.ClienteDado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDadoRepository extends JpaRepository<ClienteDado, Integer> {
}
