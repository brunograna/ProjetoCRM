package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {
}
