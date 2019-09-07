package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.CategoriaDado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDadoRepository extends JpaRepository<CategoriaDado, Integer> {
}
