package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.DadoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadoTipoRepository extends JpaRepository<DadoTipo, Integer> {
}
